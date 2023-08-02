package test;

import org.junit.Test;

/**
 * 一维数组数组的使用
 *
 * @author zt
 * @since 2023/7/13 08:18
 **/
public class OneArrayTest {

    /**
     * 类型定义
     */
    @Test
    public void test1() {
        // 两种定义方式，推荐第一种
        int[] arr1;
        int arr2[];

        // 未初始化时不能使用
//        System.out.println(arr1);
    }

    /**
     * 静态初始化
     */
    @Test
    public void test2() {
        // 静态初始化，初始化与赋值同时进行
        int[] arr1 = new int[]{1, 2, 3};
        System.out.println(arr1);

        // 静态初始化，类型推断
        double[] arr2 = {1.0, 2.0, 3.0};
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
    }

    /**
     * 动态初始化
     */
    @Test
    public void test3() {
        // 动态初始化，初始化与赋值分开进行
        int[] arr1 = new int[3];
        for (int k : arr1) {
            System.out.println(k);
        }

        // 动态初始化，不同类型的默认值
        long[] arr2 = new long[3];
        for (long k : arr2) {
            System.out.println(k);
        }
        float[] arr3 = new float[3];
        for (float k : arr3) {
            System.out.println(k);
        }
        double[] arr4 = new double[3];
        for (double k : arr4) {
            System.out.println(k);
        }
        char[] arr5 = new char[3];
        for (char k : arr5) {
            System.out.println(k);
        }
        boolean[] arr6 = new boolean[3];
        for (boolean k : arr6) {
            System.out.println(k);
        }
        String[] arr7 = new String[3];
        for (String k : arr7) {
            System.out.println(k);
        }
    }
}
