package cas;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 利用原子类实现自旋锁
 *
 * @author zt
 * @date 2024/1/18
 **/
public class SpinLockTest {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(STR."\{Thread.currentThread().getName()}\t come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(STR."\{Thread.currentThread().getName()}\t task over,unLock.........");
    }

    @Test
    public void test1() {
        Thread t1 = new Thread(() -> {
            lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unLock();
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            lock();
            unLock();
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
