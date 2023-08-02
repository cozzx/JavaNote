package demo.reverse;

import org.junit.Test;

import java.util.Arrays;

/**
 * 数组反转案例
 *
 * @author zt
 * @since 2023/8/2 12:43
 **/
public class ReverseTestCase {

    @Test
    public void test1() {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("反转之前：" + Arrays.toString(arr));
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        System.out.println("反转之后：" + Arrays.toString(arr));
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("反转之前：" + Arrays.toString(arr));

        for (int left = 0, right = arr.length - 1; left < right; left++, right--) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        System.out.println("反转之后：" + Arrays.toString(arr));
    }
}
