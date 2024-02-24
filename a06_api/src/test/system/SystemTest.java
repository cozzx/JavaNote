package test.system;

import org.junit.Test;

import java.util.Arrays;

/**
 * 系统类的使用测试
 *
 * @author zt
 * @date 2023/7/25
 **/
public class SystemTest {

    /**
     * 系统类获取时间戳
     */
    @Test
    public void test1() {
        long time = System.currentTimeMillis();
        System.out.println("现在的系统时间距离1970年1月1日凌晨：" + time + "毫秒");

        System.exit(0);
        // 程序退出后下面的代码不会执行
        System.out.println("over");
    }

    /**
     * 获取系统信息
     */
    @Test
    public void test2() {
        String javaVersion = System.getProperty("java.version");
        System.out.println("java的version:" + javaVersion);

        String javaHome = System.getProperty("java.home");
        System.out.println("java的home:" + javaHome);

        String osName = System.getProperty("os.name");
        System.out.println("os的name:" + osName);

        String osVersion = System.getProperty("os.version");
        System.out.println("os的version:" + osVersion);

        String userName = System.getProperty("user.name");
        System.out.println("user的name:" + userName);

        String userHome = System.getProperty("user.home");
        System.out.println("user的home:" + userHome);

        String userDir = System.getProperty("user.dir");
        System.out.println("user的dir:" + userDir);
    }

    /**
     * 垃圾回收
     */
    @Test
    public void test3() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            // 每一次循环 demo 就会指向新的对象，那么上次的对象就没有变量引用它了，就成垃圾对象
            GcDemo demo = new GcDemo(i);
        }

        // 如果不调用这句代码，GC可能不工作，因为当前内存很充足，GC就觉得不着急回收垃圾对象。
        // 调用这句代码，会让GC尽快来工作。
        System.gc();

        Thread.sleep(5000);
    }

    class GcDemo {
        private int value;

        public GcDemo(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "GcDemo{" + "value=" + value + '}';
        }

        // 重写finalize方法，显示垃圾回收时调用效果
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this + "我被回收了");
        }
    }

    /**
     * 数组复制
     */
    @Test
    public void test4() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[10];
        System.arraycopy(arr1, 0, arr2, 3, arr1.length);
        System.out.println(Arrays.toString(arr2)); // [0, 0, 0, 1, 2, 3, 4, 5, 0, 0]

        int[] arr3 = {1, 2, 3, 4, 5};
        System.arraycopy(arr3, 0, arr3, 1, arr3.length - 1);
        System.out.println(Arrays.toString(arr3)); // [1, 1, 2, 3, 4]

        int[] arr4 = {1, 2, 3, 4, 5};
        System.arraycopy(arr4, 1, arr4, 0, arr4.length - 1);
        System.out.println(Arrays.toString(arr4)); // [2, 3, 4, 5, 5]
    }
}
