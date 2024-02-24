package cas;

import common.Student;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子类
 *
 * @author zt
 * @date 2024/1/17
 **/
public class CasTest {

    /**
     * 原子整型
     */
    @Test
    public void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean b1 = atomicInteger.compareAndSet(5, 2024);
        System.out.println(STR."b1 result:  = \{b1}");
        boolean b2 = atomicInteger.compareAndSet(5, 2024);
        System.out.println(STR."b2 result:  = \{b2}");
    }

    /**
     * 原子引用
     */
    @Test
    public void test2() {
        AtomicReference<Student> atomicReference = new AtomicReference<>();
        Student lucy = new Student(1, "lucy", "c1");
        Student lily = new Student(2, "lily", "c4");
        atomicReference.set(lucy);
        boolean b1 = atomicReference.compareAndSet(lucy, lily);
        System.out.println(STR."b1 result:  = \{b1}");
        boolean b2 = atomicReference.compareAndSet(lucy, lily);
        System.out.println(STR."b2 result:  = \{b2}");
    }

    /**
     * 多线程环境安全用法一: 使用 synchronized 和 volatile
     */
    private volatile int count = 0;

    public synchronized void countInc() {
        count++;
    }

    public int getCount() {
        return count;
    }

    /**
     * 多线程环境安全用法二: 使用 原子类
     */
    private AtomicInteger atomicCount = new AtomicInteger(0);

    public void atomicCountInc() {
        atomicCount.incrementAndGet();
    }

    public int getAtomicCount() {
        return atomicCount.get();
    }
}
