package cas;

import common.Student;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS 中的 ABA 问题
 *
 * @author zt
 * @date 2024/1/18
 **/
public class AbaTest {

    /**
     * 测试 ABA 问题的发生
     */
    @Test
    public void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(100);

        Thread t1 = new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(101, 100);
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicInteger.compareAndSet(100, 2023);
            System.out.println(STR."t2 result: \{b}; value: \{atomicInteger.get()}");
        }, "t2");
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用 AtomicStampedReference 解决 ABA 问题
     * 单线程测试
     */
    @Test
    public void test2() {
        Student lucy = new Student(1, "lucy", "c1");
        Student lily = new Student(2, "lily", "c4");
        AtomicStampedReference<Student> atomicStampedReference = new AtomicStampedReference<>(lucy, 1);
        boolean b = atomicStampedReference.compareAndSet(lucy, lily, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(STR."\{atomicStampedReference.getReference()} = \{atomicStampedReference.getStamp()} = \{b}");
        b = atomicStampedReference.compareAndSet(lily, lucy, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(STR."\{atomicStampedReference.getReference()} = \{atomicStampedReference.getStamp()} = \{b}");
    }

    /**
     * 使用 AtomicStampedReference 解决 ABA 问题
     * 多线程测试
     */
    @Test
    public void test3() {
        Student lucy = new Student(1, "lucy", "c1");
        Student lily = new Student(2, "lily", "c4");
        Student tom = new Student(3, "tom", "c6");
        AtomicStampedReference<Student> atomicStampedReference = new AtomicStampedReference<>(lucy, 1);

        Thread t1 = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(STR."t1 first stamp: \{stamp}");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet(lucy, lily, stamp, stamp + 1);
            stamp = atomicStampedReference.getStamp();
            System.out.println(STR."t1 modify 1 result: \{b}; ref: \{atomicStampedReference.getReference()}; stamp: \{stamp}");

            b = atomicStampedReference.compareAndSet(lily, lucy, stamp, stamp + 1);
            stamp = atomicStampedReference.getStamp();
            System.out.println(STR."t1 modify 2 result: \{b}; ref: \{atomicStampedReference.getReference()}; stamp: \{stamp}");
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(STR."t2 first stamp: \{stamp}");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet(lucy, tom, stamp, stamp + 1);
            stamp = atomicStampedReference.getStamp();
            System.out.println(STR."t2 modify result: \{b}; ref: \{atomicStampedReference.getReference()}; stamp: \{stamp}");
        }, "t2");
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
