package safe;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 的使用
 *
 * @author zt
 * @date 2023/7/29
 **/
public class LockTest {

    // 1. 创建Lock的实例，必须确保多个线程共享同一个Lock实例
    private static final ReentrantLock lock = new ReentrantLock();
    private int ticket = 100;

    class TicketSale implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    // 2. 执行lock()方法，锁定对共享资源的调用
                    lock.lock();
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ticket--;
                    } else {
                        break;
                    }
                } finally {
                    // 3. unlock()的调用，释放对共享数据的锁定
                    lock.unlock();
                }
            }
        }
    }

    @Test
    public void test1() {
        // TicketSale 类中的锁是 final static，这里可以new不同实例
        Thread t1 = new Thread(new TicketSale());
        Thread t2 = new Thread(new TicketSale());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test2() {
        TicketSale window = new TicketSale();
        Thread t1 = new Thread(window);
        Thread t2 = new Thread(window);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
