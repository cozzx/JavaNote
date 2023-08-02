package demo.eigenvalues;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 特征值计算案例
 *
 * @author zt
 * @since 2023/8/1 06:46
 **/
public class EigenValuesTestCase {

    /**
     * 求数组所有元素的最大值，最小值，总和，平均值。
     */
    @Test
    public void test1() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(10);
        }
        System.out.println(Arrays.toString(arr));

        int max = arr[0];
        int min = arr[0];
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
            sum += arr[i];
        }
        System.out.println("max=" + max);
        System.out.println("min=" + min);
        System.out.println("sum=" + sum);
        System.out.println("avg=" + sum / arr.length);
    }

    /**
     * 求int数组中和最大的子数组
     */
    @Test
    public void test2() {
        int[] arr = new int[]{1, -2, 3, -10, 4, 7, -2, 5};
        int temp = 0;
        int greatestSum = 0;
        for (int i = 0; i < arr.length; i++) {

            temp += arr[i];
            if (temp < 0) {
                temp = 0;
            }
            if (temp > greatestSum) {
                greatestSum = temp;
            }
        }
        if (greatestSum == 0) {
            greatestSum = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (greatestSum < arr[i]) {
                    greatestSum = arr[i];
                }
            }
        }
        System.out.println(greatestSum);
    }
}
