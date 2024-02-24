package lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * 指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁
 *
 * @author zt
 * @date 2024/1/14
 **/
public class ReentrantLockTest {

    /**
     * synchronized 为 隐式可重入锁
     * 在一个 synchronized 修饰的方法或者代码块的内部调用本类的其他 synchronized 修饰的方法或者代码块时，是永远可以得到锁。
     */
    @Test
    public void test1() {
        System.out.println("main");
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            m1();
        }, "t1");
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main continue");
        // 对象锁和类锁不是一个, 对象锁和类锁之间没有可重入
//        Thread t2 = new Thread(this::m2, "t2");
        Thread t2 = new Thread(ReentrantLockTest::m3, "t2");
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main end");
    }

    public synchronized void m1() {
        System.out.println(STR."\{Thread.currentThread().getName()}\tm1 start");
        m2();
        System.out.println(STR."\{Thread.currentThread().getName()}\tm1 end");
    }

    public void m2() {
        synchronized (this) {
            System.out.println(STR."\{Thread.currentThread().getName()}\tm2 start");
            m3();
            System.out.println(STR."\{Thread.currentThread().getName()}\tm2 end");
        }
    }

    public static synchronized void m3() {
        System.out.println(STR."\{Thread.currentThread().getName()}\tm3 start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(STR."\{Thread.currentThread().getName()}\tm3 end");
    }

    /**
     * ReentrantLock 显式可重入锁
     * 加锁几次就要解锁几次, 否则会导致死锁
     */
    @Test
    public void test2() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1外层调用");
                try {
                    lock.lock();
                    System.out.println("t1内层调用");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }

        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t2外层调用");
                try {
                    lock.lock();
                    System.out.println("t2内层调用");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }

        }, "t2");
        t2.start();

        t1.join();
        t2.join();
    }
}
