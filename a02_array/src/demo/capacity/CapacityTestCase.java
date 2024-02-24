package demo.capacity;

import org.junit.Test;

import java.util.Arrays;

/**
 * 数组扩容缩容案例
 *
 * @author zt
 * @date 2023/8/2
 **/
public class CapacityTestCase {

    /**
     * 数组扩容一倍
     */
    @Test
    public void test1() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] newArr = new int[arr.length << 1];

        for (int i = 0; i < newArr.length; i++) {
            if (i < arr.length) {
                newArr[i] = arr[i];
            } else {
                newArr[i] = newArr[i - 1] + 1;
            }
        }

        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 数组删除一个元素
     */
    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int delIndex = 2;

        // 方案1：
        int[] newArr = new int[arr.length - 1];
        for (int i = 0; i < delIndex; i++) {
            newArr[i] = arr[i];
        }
        for (int i = delIndex + 1; i < arr.length; i++) {
            newArr[i - 1] = arr[i];
        }
        System.out.println(Arrays.toString(newArr));

        // 方案2：
        for (int i = delIndex; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = 0;

        System.out.println(Arrays.toString(arr));
    }
}
