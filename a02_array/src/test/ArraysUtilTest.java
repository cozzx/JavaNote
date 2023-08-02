package test;

import org.junit.Test;

import java.util.Arrays;

/**
 * Arrays 工具类测试
 *
 * @author zt
 * @since 2023/8/2 11:19
 **/
public class ArraysUtilTest {

    /**
     * equals 比较两个数组的元素是否依次相等
     */
    @Test
    public void test1() {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{1, 2, 3, 4, 5};
        System.out.println(arr1 == arr2);
        System.out.println(Arrays.equals(arr1, arr2));
    }

    /**
     * toString 输出数组元素信息
     */
    @Test
    public void test2() {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        System.out.println(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * fill 将指定值填充到数组之中
     */
    @Test
    public void test3() {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        Arrays.fill(arr1, 10);
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * sort 使用快速排序算法实现的排序
     */
    @Test
    public void test4() {
        int[] arr1 = new int[]{34, 54, 3, 2, 65, 7, 34, 5, 76, 34, 67};
        Arrays.sort(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * binarySearch 二分查找
     */
    @Test
    public void test5() {
        int[] arr1 = new int[]{1, 2, 3, 4, 36};
        int index = Arrays.binarySearch(arr1, 35);
        System.out.println(index);
        if (index >= 0) {
            System.out.println("找到了，索引位置为：" + index);
        } else {
            System.out.println("未找到");
        }
    }
}
