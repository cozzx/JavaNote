package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zt
 * @date 2024/5/13
 **/
public class EventLoopTest {
    @Test
    public void test() {
        BossEventLoop bossEventLoop = new BossEventLoop();
        bossEventLoop.register();
    }

    static class BossEventLoop implements Runnable {
        private Selector bossSelector;
        private WorkerEventLoop[] workerEventLoops;
        private volatile boolean start = false;
        AtomicInteger index = new AtomicInteger();

        public void register() {
            try {
                if (!start) {
                    bossSelector = Selector.open();
                    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                    serverSocketChannel.bind(new InetSocketAddress(8880));
                    serverSocketChannel.configureBlocking(false);
                    serverSocketChannel.register(bossSelector, SelectionKey.OP_ACCEPT);
                    initWorkerEventLoop();
                    new Thread(this, "boss").start();
                    System.out.println("boss start...");
                    start = true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        public void initWorkerEventLoop() {
            workerEventLoops = new WorkerEventLoop[Runtime.getRuntime().availableProcessors()];
            for (int i = 0; i < workerEventLoops.length; i++) {
                workerEventLoops[i] = new WorkerEventLoop(i);
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    bossSelector.select();
                    Iterator<SelectionKey> iter = bossSelector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey selectionKey = iter.next();
                        iter.remove();
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            System.out.println("connected: " + socketChannel.getRemoteAddress());
                            workerEventLoops[index.getAndIncrement() % workerEventLoops.length].register(socketChannel);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class WorkerEventLoop implements Runnable {
        private Selector workerSelector;
        private volatile boolean start = false;
        private int index;
        private ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<>();

        public WorkerEventLoop(int index) {
            this.index = index;
        }

        public void register(SocketChannel socketChannel) {
            try {
                if (!start) {
                    workerSelector = Selector.open();
                    new Thread(this, "worker-" + index).start();
                    start = true;
                }
                tasks.add(() -> {
                    try {
                        socketChannel.register(workerSelector, SelectionKey.OP_READ);
                        workerSelector.selectNow();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                workerSelector.wakeup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    workerSelector.select();
                    Runnable task = tasks.poll();
                    if (task != null) {
                        task.run();
                    }
                    Iterator<SelectionKey> iter = workerSelector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey selectionKey = iter.next();
                        if (selectionKey.isReadable()) {
                            SocketChannel sc = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(128);
                            try {
                                int read = sc.read(buffer);
                                if (read == -1) {
                                    selectionKey.cancel();
                                    sc.close();
                                } else {
                                    buffer.flip();
                                    System.out.println("message:" + sc.getRemoteAddress());
                                    ByteBufferDebug.debugAll(buffer);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                selectionKey.cancel();
                                sc.close();
                            }
                        }
                        iter.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
