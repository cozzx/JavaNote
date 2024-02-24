package lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author zt
 * @date 2024/1/14
 **/
public class DeadLockTest {
    final Object o1 = new Object();
    final Object o2 = new Object();

    @Test
    public void test() {
        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                System.out.println("t1: o1 run");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                
                synchronized (o2) {
                    System.out.println("t1: o2 run");
                }
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (o2) {
                System.out.println("t2: o2 run");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (o1) {
                    System.out.println("t2: o1 run");
                }
            }
        }, "t2");
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
