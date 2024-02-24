package test;

import org.junit.Test;

/**
 * 多维数组的使用
 *
 * @author zt
 * @date 2023/7/13
 **/
public class MultiArrayTest {

    /**
     * 类型定义
     */
    @Test
    public void test1() {
        // 两种定义方式，推荐第一种
        double[][][] arr1;
        double[][] arr2[];
        double[] arr3[][];
        double arr4[][][];

        // 未初始化时不能使用
//        System.out.println(arr1);
    }

    /**
     * 静态初始化
     */
    @Test
    public void test2() {
        // 静态初始化，初始化与赋值同时进行
        int[][] arr1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9, 10}};
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                System.out.println(arr1[i][j]);
            }
        }

        // 静态初始化，类型推断
        // 使用增强for循环
        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9, 10}};
        for (int[] value : arr2) {
            for (int i : value) {
                System.out.println(i);
            }
        }
    }

    /**
     * 动态初始化
     */
    @Test
    public void test3() {
        // 动态初始化，初始化与赋值分开进行
        int[][] arr1 = new int[3][2];
        arr1[1][1] = 100;
        for (int[] ints : arr1) {
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        }
        System.out.println();

        // 初始化默认值
        int[][] arr2 = new int[3][];
        System.out.println(arr2[0]); // null
//        System.out.println(arr2[0][0]); // NulPointException

        String[][] arr3 = new String[4][2];
        System.out.println(arr3[0]); // [Ljava.lang.String;@6956de9
        System.out.println(arr3[0][1]); // null
    }
}
