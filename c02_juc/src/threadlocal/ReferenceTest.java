package threadlocal;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 强/软/弱/虚 四种引用
 *
 * @author zt
 * @date 2024/1/19
 **/
public class ReferenceTest {

    public class ObjDemo {
        public int field = 123;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("ObjDemo 对象被回收");
        }
    }

    /**
     * 强引用, 执行需要配置 jvm 参数 -Xms10m -Xmx10m
     * 结果即使出现 OOM 也不会回收对象
     */
    @Test
    public void test1() {
        ObjDemo demo = new ObjDemo();
        byte[] bytes = new byte[10 * 1024 * 1024];
        System.out.println(STR."bytes.length = \{bytes.length}");
    }

    /**
     * 软引用, 执行需要配置 jvm 参数 -Xms10m -Xmx10m
     * GC 时 如果空间够用不回收, 不够用再回收
     */
    @Test
    public void test2() {
        SoftReference<ObjDemo> objDemoSoftReference = new SoftReference<>(new ObjDemo());
        List<byte[]> bytes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            bytes.add(new byte[i * 1024 * 1024]);
            System.gc();
            System.out.println("第" + i + "次 gc");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(STR."bytes.length = \{bytes.size()}");
    }

    /**
     * 弱引用
     * GC 时一定会回收
     */
    @Test
    public void test3() {
        WeakReference<ObjDemo> objDemoWeakReference = new WeakReference<>(new ObjDemo());
        System.out.println(Objects.requireNonNull(objDemoWeakReference.get()).field);
        byte[] bytes = new byte[10 * 1024 * 1024];
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(STR."bytes.length = \{bytes.length}");
    }

    /**
     * 虚引用, 执行需要注释掉目标对象类的 finalize 方法
     * 必须和引用队列 ReferenceQueue 一起使用
     * 虚引用不指向对象, get 不到对象的内容
     * 在任何时候都会被垃圾回收器回收
     * 当被 GC 回收时, 会加入到引用队列中, 实现比 finalize 机制更灵活的回收操作
     */
    @Test
    public void test4() {
        ReferenceQueue<ObjDemo> objectReferenceQueue = new ReferenceQueue<>();
        PhantomReference<ObjDemo> objectPhantomReference = new PhantomReference<>(new ObjDemo(), objectReferenceQueue);
        // 虚引用不指向对象
        // System.out.println(Objects.requireNonNull(objectPhantomReference.get()).field);

        System.gc();
        while (true) {
            if (objectReferenceQueue.poll() != null) {
                System.out.println("虚引用回收到了引用队列");
                break;
            }
        }
    }
}
