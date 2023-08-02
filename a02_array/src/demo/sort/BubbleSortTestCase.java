package demo.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 数组排序 - 冒泡排序
 *
 * @author zt
 * @since 2023/8/2 12:16
 **/
public class BubbleSortTestCase {

    @Test
    public void test1() {
        int[] arr = new int[]{3, 6, 4, 2, 11, 10, 5};
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void test2() {
        int[] arr = new int[]{3, 6, 4, 2, 11, 10, 5};
        for (int i = 1; i < arr.length; i++) {
            // 假设数组已经是有序的
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 如果元素发生了交换，那么说明数组还没有排好序
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

}
