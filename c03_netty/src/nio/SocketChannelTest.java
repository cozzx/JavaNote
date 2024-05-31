package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NIO 网络操作
 *
 * @author zt
 * @date 2024/5/10
 **/
public class SocketChannelTest {

    /**
     * 客户端
     */
    @Test
    public void testClient() {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress("localhost", 8880));
            SocketAddress address = socketChannel.getLocalAddress();
            System.out.println("client waiting... " + address);
            Scanner input = new Scanner(System.in);
            String next = input.next();
            socketChannel.write(ByteBuffer.wrap(next.getBytes()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * 服务端 单线程阻塞
     */
    @Test
    public void testServer() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 创建服务器
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            // 绑定监听端口
            serverSocketChannel.bind(new InetSocketAddress(8880));
            // 设置连接集合
            List<SocketChannel> socketChannelList = new ArrayList<>();
            while (true) {
                // accept 接收客户端的连接，socketChannel 用来与客户端之间通信
                System.out.println("before connect ...");
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("after connect ...");
                socketChannelList.add(socketChannel);
                // 接收客户端发送的数据
                for (SocketChannel channel : socketChannelList) {
                    System.out.println("before read ...");
                    channel.read(buffer);
                    buffer.flip();
                    ByteBufferDebug.debugRead(buffer);
                    buffer.clear();
                    System.out.println("after read ...");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 服务端 单线程非阻塞
     */
    @Test
    public void testServerNb() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 创建服务器
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            // 绑定监听端口
            serverSocketChannel.bind(new InetSocketAddress(8880));
            // 非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 设置连接集合
            List<SocketChannel> socketChannelList = new ArrayList<>();
            while (true) {
                // accept 接收客户端的连接，socketChannel 用来与客户端之间通信
                // System.out.println("before connect ...");
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    System.out.println("after connect ...");
                    // 非阻塞模式
                    socketChannel.configureBlocking(false);
                    socketChannelList.add(socketChannel);
                }
                // 接收客户端发送的数据
                for (SocketChannel channel : socketChannelList) {
                    // System.out.println("before read ...");
                    // 非阻塞，线程仍然会继续运行，如果没有读到数据，read 返回 0
                    int read = channel.read(buffer);
                    if (read > 0) {
                        buffer.flip();
                        ByteBufferDebug.debugRead(buffer);
                        buffer.clear();
                        System.out.println("after read...");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 服务端 selector
     */
    @Test
    public void testServerSelector() {
        // 创建 selector，管理多个 Channel
        try (Selector selector = Selector.open()) {
            // 创建服务器
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8880));
            serverSocketChannel.configureBlocking(false);
            // 建立 selector 和 channel 的联系（注册）
            // SelectionKey 就是将来事件发生后，通过它可以知道是什么事件和哪个channel的事件
            SelectionKey sscKey = serverSocketChannel.register(selector, 0, null);
            // 设置 key 只关注 accept 事件
            sscKey.interestOps(SelectionKey.OP_ACCEPT);
            System.out.println("sscKey = " + sscKey);
            while (true) {
                // select 方法，没有事件就阻塞，有事件线程恢复运行
                // select 在事件未处理时，它不会阻塞, 事件发生后要么处理，要么取消
                selector.select();
                // 处理事件，selectedKeys 内部包含了所有发生的事件
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey sessionKey = iterator.next();
                    System.out.println("sessionKey = " + sessionKey);
                    // 处理key 时，要从 selectedKeys 集合中删除，否则下次处理就会有问题
                    iterator.remove();
                    // 区分事件的类型
                    if (sessionKey.isAcceptable()) {
                        // 拿到触发事件的 channel
                        // accept 事件的 channel 就是上面的 serverSocketChannel，因此下面一行可以省略，直接用上面的 serverSocketChannel
//                        ServerSocketChannel channel = (ServerSocketChannel) sessionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        System.out.println("socketChannel = " + socketChannel);

                        // 每个channel需要维护一个独立的bytebuffer
                        // 将一个 byteBuffer 作为附件关联到 selectionKey 上
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        SelectionKey scKey = socketChannel.register(selector, 0, buffer);
                        scKey.interestOps(SelectionKey.OP_READ);
                        System.out.println("scKey = " + scKey);

                        // 测试可写事件，模拟客户端连接上之后 服务端向客户端发送大量信息
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < 5000000; i++) {
                            stringBuilder.append("a");
                        }
                        ByteBuffer byteBuffer = Charset.defaultCharset().encode(stringBuilder.toString());
                        socketChannel.write(byteBuffer);
                        // 数据的发送受各种方面的影响，如果一次性写不完，判断还有没有剩余内容，不要进行循环写入
//                        while (byteBuffer.hasRemaining()) {
//                            int write = socketChannel.write(byteBuffer);
//                            System.out.println("write = " + write);
//                        }
                        // 要加入监听可写事件，通过监听可写事件再写入
                        if (byteBuffer.hasRemaining()) {
                            scKey.interestOps(scKey.interestOps() + SelectionKey.OP_WRITE);
                            // 把未写完的数据挂到 scKey 上，下次循环处理
                            scKey.attach(byteBuffer);
                        }
                    } else if (sessionKey.isReadable()) {
                        try {
                            // 拿到触发事件的 channel
                            SocketChannel channel = (SocketChannel) sessionKey.channel();
                            // 获取 selectionKey 上关联的附件
                            ByteBuffer buffer = (ByteBuffer) sessionKey.attachment();
                            // 如果是正常断开，read 的方法的返回值是 -1
                            int read = channel.read(buffer);
                            if (read == -1) {
                                System.out.println("read: -1");
                                // 从 selector 的 keys 集合中真正删除 key
                                sessionKey.cancel();
                            } else {
                                ByteBufferTest bt = new ByteBufferTest();
                                bt.split(buffer);
                                // 需要扩容
                                if (buffer.position() == buffer.limit()) {
                                    ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                    buffer.flip();
                                    // 0123456789abcdef3333\n
                                    newBuffer.put(buffer);
                                    sessionKey.attach(newBuffer);
                                    System.out.println("buffer 扩容");
                                }
                                System.out.println("read content: " + buffer);
                            }
                        } catch (IOException | NullPointerException e) {
                            e.printStackTrace();
                            // 因为客户端断开了,因此需要将 key 取消（从 selector 的 keys 集合中真正删除 key）
                            sessionKey.cancel();
                        }
                    } else if (sessionKey.isWritable()) {
                        ByteBuffer byteBuffer = (ByteBuffer) sessionKey.attachment();
                        SocketChannel socketChannel = (SocketChannel) sessionKey.channel();
                        int write = socketChannel.write(byteBuffer);
                        System.out.println(write);
                        if (!byteBuffer.hasRemaining()) {
                            // 清除buffer
                            sessionKey.attach(null);
                            sessionKey.interestOps(sessionKey.interestOps() - SelectionKey.OP_WRITE);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testClientSelector() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8880));
        // 接收数据
        int count = 0;
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            count += socketChannel.read(buffer);
            System.out.println("count = " + count);
            buffer.clear();
        }
    }

    @Test
    public void testMultiThreadServer() {
        Thread.currentThread().setName("boss");
        try (
                Selector bossSelector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ) {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(8880));
            serverSocketChannel.register(bossSelector, SelectionKey.OP_ACCEPT);
            System.out.println(Runtime.getRuntime().availableProcessors());

            // 创建固定数量的 worker 并初始化
            Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
            for (int i = 0; i < workers.length; i++) {
                workers[i] = new Worker("worker-" + i);
            }
            AtomicInteger index = new AtomicInteger();

            while (true) {
                bossSelector.select();
                Iterator<SelectionKey> iterator = bossSelector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        System.out.println("connected... " + socketChannel.getRemoteAddress());

                        // 关联 selector，round robin 轮询
                        workers[index.getAndIncrement() % workers.length].register(socketChannel);
                        System.out.println("registered... " + socketChannel.getRemoteAddress());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    static class Worker implements Runnable {
        private Thread thread;
        private Selector selector;
        private String name;
        private volatile boolean start = false;
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public Worker(String name) {
            this.name = name;
        }

        // 初始化线程，和 selector
        public void register(SocketChannel socketChannel) {
            try {
                if (!start) {
                    selector = Selector.open();
                    thread = new Thread(this, name);
                    thread.start();
                    start = true;
                }
                selector.wakeup();
                socketChannel.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            try {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        System.out.println("read... " + channel.getRemoteAddress());
                        channel.read(buffer);
                        buffer.flip();
                        ByteBufferDebug.debugAll(buffer);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
