package communication;

import org.junit.Test;

/**
 * 线程通信：等待唤醒机制
 *
 * @author zt
 * @since 2023/7/29 09:49
 **/
public class WaitNotifyTest {

    class PrintNum implements Runnable {
        int i = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    if (i <= 100) {
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 线程1执行一阵，线程2执行一阵
     */
    @Test
    public void test1() {
        PrintNum printNum = new PrintNum();
        Thread t1 = new Thread(printNum, "Thread-1");
        Thread t2 = new Thread(printNum, "Thread-2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    class PrintNumAlternate implements Runnable {
        int i = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    notify();
                    if (i <= 100) {
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                    } else {
                        break;
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 线程1输出一个，线程2输出一个，交替输出
     */
    @Test
    public void test2() {
        PrintNumAlternate printNum = new PrintNumAlternate();
        Thread t1 = new Thread(printNum, "Thread-1");
        Thread t2 = new Thread(printNum, "Thread-2");
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
