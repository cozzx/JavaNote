package interrupt;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 等待唤醒机制
 *
 * @author zt
 * @date 2024/1/15
 **/
public class LockSupportTest {

    /**
     * 方式一: 使用 Object 对象的 wait() notify() 方法
     */
    final Object lockObj = new Object();

    @Test
    public void test1() {
        Thread t1 = new Thread(() -> {
            synchronized (lockObj) {
                System.out.println("t1: start");
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1: continue");

            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (lockObj) {
                lockObj.notify();
                System.out.println("t2: notify");
            }
        }, "t2").start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方式二: 使用 Condition 接口 await() signal() 方法
     */
    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    @Test
    public void test2() {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1: start");
                condition.await();
                System.out.println("t1: continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            condition.signal();
            System.out.println("t2: notify");
            lock.unlock();
        }, "t2").start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方式三: 使用 LockSupport 接口的 park() unpark() 方法
     */
    @Test
    public void test3() {
        Thread t1 = new Thread(() -> {
            System.out.println("t1: start");
            LockSupport.park();
            System.out.println("t1: continue");
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println("t2: notify");
        }, "t2").start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
