package test.system;

import org.junit.Test;

/**
 * 运行时类，与运行的环境相关
 *
 * @author zt
 * @date 2023/7/25
 **/
public class RuntimeTest {

    @Test
    public void test1() {

        Runtime runtime = Runtime.getRuntime();

        // Java 虚拟机中初始化时的内存总量。此方法返回的值可能随时间的推移而变化，这取决于主机环境。默认为物理电脑内存的1/64。
        long initialMemory = runtime.totalMemory();

        // Java 虚拟机中最大程度能使用的内存总量。默认为物理电脑内存的1/4。
        long maxMemory = runtime.maxMemory();

        // 模拟内存占用
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += i;
        }

        // Java 虚拟机中的空闲内存量。调用 gc 方法可能导致 freeMemory 返回值的增加。
        long freeMemory = runtime.freeMemory();

        System.out.println("虚拟机初始化堆内存总量：" + initialMemory / 1024 / 1024 + "MB");
        System.out.println("虚拟机初最大堆内存总量：" + maxMemory / 1024 / 1024 + "MB");
        System.out.println("空闲内存：" + freeMemory / 1024 / 1024 + "MB");
        System.out.println("已用内存：" + (initialMemory - freeMemory) / 1024 / 1024 + "MB");
    }
}
