package objectlayout;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;

/**
 * 对象内存布局 - 对象头分析
 *
 * @author zt
 * @date 2024/1/19
 **/
public class MarkWordTest {

    /**
     * 查看对象头字节占用
     */
    @Test
    public void test1() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        Object[] objectArray = new Object[]{};
        System.out.println(ClassLayout.parseInstance(objectArray).toPrintable());
    }


    /**
     * 查看类中不同类型的字段在对象示例数据中的占用
     * 不开启压缩的虚拟机参数: -XX:-UseCompressedClassPointers -XX:-UseCompressedOops
     */
    class DataType {
        boolean b;
        byte bt;
        short st;
        int i;
        long j;
        float f;
        double d;
        char c;
        String s = "123";
    }

    @Test
    public void test2() {
        DataType dataType = new DataType();
        System.out.println(ClassLayout.parseInstance(dataType).toPrintable());
    }


    /**
     * 验证 HashCode
     */
    @Test
    public void test3() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        System.out.println(Integer.toHexString(object.hashCode()));
        System.out.println(Integer.toBinaryString(object.hashCode()));
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object) {
            System.out.println(Integer.toHexString(object.hashCode()));
            System.out.println(Integer.toBinaryString(object.hashCode()));
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

    /**
     * 验证 轻量锁标记
     */
    @Test
    public void test4() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

    /**
     * 验证 重量锁标记
     */
    @Test
    public void test5() throws InterruptedException {
        Object object = new Object();
        CountDownLatch count = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    synchronized (object) {
                        System.out.println(ClassLayout.parseInstance(object).toPrintable());
                    }
                } finally {
                    count.countDown();
                }
            }, STR."t\{i}").start();
        }
        count.await();
        System.out.println("finished");
    }
}
