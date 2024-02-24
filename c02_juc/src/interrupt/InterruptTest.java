package interrupt;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 中断的相关 API
 *
 * @author zt
 * @date 2024/1/15
 **/
public class InterruptTest {

    /**
     * 调用 interrupt() 方法会修改线程的中断标识位, 不会主动打断线程
     * 可在线程中通过判断标识位, 主动停止
     */
    @Test
    public void test1() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("t1 线程被打断, 中断标志位：" + Thread.currentThread().isInterrupted());
                    break;
                } else {
                    System.out.println("t1: " + i + ", 中断标志位：" + Thread.currentThread().isInterrupted());
                }
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();
        System.out.println("t1 线程调用 interrupt() 后 t1 的中断标志位：" + t1.isInterrupted());

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t1 线程结束后的中断标志位：" + t1.isInterrupted());
    }

    /**
     * 当线程中使用 sleep() join() 等方法时, 打断该线程会触发 InterruptedException
     */
    @Test
    public void test2() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("t1 线程被打断, 中断标志位：" + Thread.currentThread().isInterrupted());
                    break;
                } else {
                    System.out.println("t1: " + i + ", 中断标志位：" + Thread.currentThread().isInterrupted());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("该线程被打断, 触发InterruptedException 异常");
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();
        System.out.println("t1 线程调用 interrupt() 后 t1 的中断标志位：" + t1.isInterrupted());

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t1 线程结束后的中断标志位：" + t1.isInterrupted());
    }

    /**
     * 使用静态方法 interrupted() 判断当前线程是否被打断, 并清除当前中断状态(中断标志位)
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("-----------1");
        Thread.currentThread().interrupt();
        System.out.println("-----------2");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
    }

    /**
     * volatile 变量
     */
    public volatile boolean isStop = false;

    @Test
    public void test3() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                if (isStop) {
                    System.out.println("volatile 值改变, t1 线程停止");
                    break;
                }
                System.out.println("t1 线程进行中 - " + i);
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            isStop = true;
        }, "t2");
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * AtomicBoolean 类
     */
    public AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    @Test
    public void test4() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                if (atomicBoolean.get()) {
                    System.out.println("atomicBoolean 值改变, t1 线程停止");
                    break;
                }
                System.out.println("t1 线程进行中 - " + i);
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2");
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
