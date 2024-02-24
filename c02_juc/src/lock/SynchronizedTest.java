package lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author zt
 * @date 2024/1/14
 **/
public class SynchronizedTest {

    public synchronized void s1() throws InterruptedException {
        System.out.println("s1: 1");
        TimeUnit.MILLISECONDS.sleep(800);
        System.out.println("s1: 2");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("s1: 3");
    }

    public void s2() throws InterruptedException {
        synchronized (this) {
            System.out.println("s2: 1");
            TimeUnit.MILLISECONDS.sleep(700);
            System.out.println("s2: 2");
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("s2: 3");
        }
    }

    public static synchronized void s3() throws InterruptedException {
        System.out.println("s3: 1");
        TimeUnit.MILLISECONDS.sleep(600);
        System.out.println("s3: 2");
        TimeUnit.MILLISECONDS.sleep(400);
        System.out.println("s3: 3");
    }

    @Test
    public void test() throws InterruptedException {
        Thread s1 = new Thread(() -> {
            try {
                s1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "s1");
        s1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread s2 = new Thread(() -> {
            try {
                s2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "s2");
        s2.start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread s3 = new Thread(() -> {
            try {
                s3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "s3");
        s3.start();

        s1.join();
        s2.join();
        s3.join();
    }
}
