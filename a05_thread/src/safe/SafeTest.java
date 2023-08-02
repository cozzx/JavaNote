package safe;

import org.junit.Test;

/**
 * 线程安全测试
 *
 * @author zt
 * @since 2023/7/27 19:21
 **/
public class SafeTest {

    /**
     * 静态方法加锁
     */
    class TicketSaleThread extends Thread {
        private static int ticket = 100;

        public void run() {
            while (ticket > 0) {
                saleOneTicket();
            }
        }

        /**
         * 锁对象是TicketSaleThread类的Class对象，而一个类的Class对象在内存中肯定只有一个
         */
        public synchronized static void saleOneTicket() {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出一张票，票号:" + ticket);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket--;
            }
        }
    }

    @Test
    public void test1() {
        /**
         * 静态方法加锁，锁对象是TicketSaleThread类的Class对象，而一个类的Class对象在内存中肯定只有一个
         */
        TicketSaleThread t1 = new TicketSaleThread();
        TicketSaleThread t2 = new TicketSaleThread();
        TicketSaleThread t3 = new TicketSaleThread();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 非静态方法加锁
     */
    class TicketSaleThread2 implements Runnable {
        private int ticket = 100;

        @Override
        public void run() {
            while (ticket > 0) {
                saleOneTicket();
            }
        }

        /**
         * 锁对象是this，3个线程必须使用同一个TicketSaleRunnable对象
         */
        public synchronized void saleOneTicket() {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出一张票，票号:" + ticket);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket--;
            }
        }
    }

    @Test
    public void test2() {
        /**
         * 非静态方法加锁，锁对象是this，3个线程必须使用同一个TicketSaleRunnable对象
         */
        TicketSaleThread2 t = new TicketSaleThread2();
        Thread t1 = new Thread(t, "窗口1");
        Thread t2 = new Thread(t, "窗口2");
        Thread t3 = new Thread(t, "窗口3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 同步代码块
     */
    class TicketSaleThread3 implements Runnable {
        private int ticket = 100;

        @Override
        public void run() {
            while (ticket > 0) {
                saleOneTicket();
            }
        }

        /**
         * 锁对象是this，3个线程必须使用同一个TicketSaleRunnable对象
         */
        public void saleOneTicket() {
            // 其他逻辑

            synchronized (this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出一张票，票号:" + ticket);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket--;
                }
            }

            // 其他逻辑
        }
    }

    @Test
    public void test3() {
        /**
         * 使用同步代码块，锁对象是this，3个线程必须使用同一个TicketSaleRunnable对象
         */
        TicketSaleThread3 t = new TicketSaleThread3();
        Thread t1 = new Thread(t, "窗口1");
        Thread t2 = new Thread(t, "窗口2");
        Thread t3 = new Thread(t, "窗口3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
