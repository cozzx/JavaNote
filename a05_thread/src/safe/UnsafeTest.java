package safe;

import org.junit.Test;

/**
 * 线程不安全测试
 *
 * @author zt
 * @date 2023/7/27 07:23
 **/
public class UnsafeTest {

    /**
     * 局部变量不能共享
     */
    class TicketWindow1 extends Thread {
        @Override
        public void run() {
            int ticket = 100;
            while (ticket > 0) {
                System.out.println(getName() + "卖出一张票，票号:" + ticket);
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

        TicketWindow1 t1 = new TicketWindow1();
        TicketWindow1 t2 = new TicketWindow1();
        TicketWindow1 t3 = new TicketWindow1();

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
     * 不同对象的实例变量不共享
     */
    class TicketWindow2 extends Thread {
        private int ticket = 100;

        public void run() {
            while (ticket > 0) {
                System.out.println(getName() + "卖出一张票，票号:" + ticket);
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

        TicketWindow2 t1 = new TicketWindow2();
        TicketWindow2 t2 = new TicketWindow2();
        TicketWindow2 t3 = new TicketWindow2();

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
     * 静态变量是共享的，有重复票或负数票问题
     */
    class TicketWindow3 extends Thread {
        private static int ticket = 100;

        public void run() {
            while (ticket > 0) {
                System.out.println(getName() + "卖出一张票，票号:" + ticket);
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
    public void test3() {

        TicketWindow3 t1 = new TicketWindow3();
        TicketWindow3 t2 = new TicketWindow3();
        TicketWindow3 t3 = new TicketWindow3();

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
     * 同一个对象的实例变量共享，有重复票或负数票问题
     */
    class TicketWindow4 implements Runnable {
        private int ticket = 100;

        @Override
        public void run() {
            while (ticket > 0) {
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
    public void test4() {

        TicketWindow4 w = new TicketWindow4();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

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
     * 资源类对象共享，有重复票或负数票问题
     */
    class Ticket {
        private int ticket = 100;

        public void sale() {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出一张票，票号:" + ticket);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket--;
            } else {
                throw new RuntimeException("没有票了");
            }
        }

        public int getTicket() {
            return ticket;
        }
    }

    @Test
    public void test5() {

        Ticket ticket = new Ticket();
        // 启动多个线程操作资源类的对象
        Thread t1 = new Thread("窗口一") {
            public void run() {
                while (ticket.getTicket() > 0) {
                    ticket.sale();
                }
            }
        };
        Thread t2 = new Thread("窗口二") {
            public void run() {
                while (ticket.getTicket() > 0) {
                    ticket.sale();
                }
            }
        };
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                while (ticket.getTicket() > 0) {
                    ticket.sale();
                }
            }
        }, "窗口三");

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
