package communication;

import org.junit.Test;

/**
 * 生产者消费者问题
 *
 * @author zt
 * @since 2023/7/29 10:22
 **/
public class ProducerConsumerTest {

    class Clerk {
        private int productNum = 0;
        private static final int MAX_PRODUCT = 20;
        private static final int MIN_PRODUCT = 1;

        public synchronized void add() {
            if (productNum < MAX_PRODUCT) {
                productNum++;
                System.out.println(Thread.currentThread().getName() + "生产了第" + productNum + "个产品");
                // 唤醒消费者
                this.notifyAll();
            } else {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void minus() {
            if (productNum > MIN_PRODUCT) {
                productNum--;
                System.out.println(Thread.currentThread().getName() + "消费了第" + productNum + "个产品");
                this.notifyAll();
            } else {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class Producer extends Thread {
        private Clerk clerk;

        public Producer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            System.out.println("=========生产者开始生产产品========");
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 要求clerk去增加产品
                clerk.add();
            }
        }
    }

    class Consumer extends Thread {
        private Clerk clerk;

        public Consumer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            System.out.println("=========消费者开始消费产品========");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 要求clerk去减少产品
                clerk.minus();
            }
        }
    }

    @Test
    public void test() {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);

        Consumer c1 = new Consumer(clerk);
        Consumer c2 = new Consumer(clerk);

        p1.setName("生产者1");
        c1.setName("消费者1");
        c2.setName("消费者2");

        p1.start();
        c1.start();
        c2.start();

        try {
            p1.join();
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
