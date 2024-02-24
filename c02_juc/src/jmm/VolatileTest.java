package jmm;

import org.junit.Test;

/**
 * @author zt
 * @date 2024/1/16
 **/
public class VolatileTest {

    /**
     * enable 变量不加 volatile 会导致 enable 变量在 t1 线程的变化不能通知到主线程
     * System.out.println 语句会触发 MESI 缓存一致性协议
     */
    private volatile boolean enable = true;

    @Test
    public void test() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            enable = false;
        }, "t1");
        t1.start();

        long i = 0;
        while (enable) {
            i++;
        }
        System.out.println(STR."i = \{i}");
    }

    /**
     * 读写策略
     */
    private volatile int value;

    public int getValue() {
        // 利用 volatile 保证读取操作的可见性
        return value;
    }

    public synchronized int increment() {
        // 利用 synchronized 保证复合操作的原子性
        return value++;
    }


    /**
     * DCL 双端锁的发布
     */
    public static class SafeDoubleCheckSingleton {
        private static volatile SafeDoubleCheckSingleton singleton;

        private SafeDoubleCheckSingleton() {

        }

        // 双重锁设计
        public static SafeDoubleCheckSingleton getInstance() {
            if (singleton == null) {
                // 多线程并发创建对象时,会通过加锁保证只有一个线程能够创造对象
                synchronized (SafeDoubleCheckSingleton.class) {
                    if (singleton == null) {
                        // 隐患: 多线程环境下, 由于重排序, 该对象可能还未完成初始化就被其他线程读取
                        // 解决原理: 利用 volatile, 禁止 "初始化对象" 和 "设置 singleton 指向内存空间" 的重排序
                        singleton = new SafeDoubleCheckSingleton();
                    }
                }
            }
            return singleton;
        }
    }
}
