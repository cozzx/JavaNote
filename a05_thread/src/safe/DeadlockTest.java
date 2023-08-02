package safe;

import org.junit.Test;

/**
 * 死锁测试
 *
 * @author zt
 * @since 2023/7/29 07:08
 **/
public class DeadlockTest {

    @Test
    public void test1() {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        // thread
        new Thread("thread-1") {
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);

                    }
                }
            }
        }.start();

        // main
        synchronized (s2) {
            s1.append("c");
            s2.append("3");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (s1) {
                s1.append("d");
                s2.append("4");

                System.out.println(s1);
                System.out.println(s2);

            }

        }
    }

    @Test
    public void test2() {
        Object g = new Object();
        Object m = new Object();
        Owner s = new Owner(g, m);
        Customer c = new Customer(g, m);
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Owner implements Runnable {
        private Object goods;
        private Object money;

        public Owner(Object goods, Object money) {
            super();
            this.goods = goods;
            this.money = money;
        }

        @Override
        public void run() {
            synchronized (goods) {
                System.out.println("先给钱");
                synchronized (money) {
                    System.out.println("发货");
                }
            }
        }
    }

    class Customer implements Runnable {
        private Object goods;
        private Object money;

        public Customer(Object goods, Object money) {
            super();
            this.goods = goods;
            this.money = money;
        }

        @Override
        public void run() {
            synchronized (money) {
                System.out.println("先发货");
                synchronized (goods) {
                    System.out.println("再给钱");
                }
            }
        }
    }
}
