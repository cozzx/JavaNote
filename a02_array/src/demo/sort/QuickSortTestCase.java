package demo.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 数组排序 - 快速排序
 *
 * @author zt
 * @date 2023/8/2
 **/
public class QuickSortTestCase {

    @Test
    public void test1() {
        int[] arr = {9, -16, 30, 23, -30, -49, 25, 21, 30, 12};
        System.out.println("排序之前：" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("\n排序之后：" + Arrays.toString(arr));
    }

    public void quickSort(int[] data, int start, int end) {
        if (start < end) {
            int base = data[start];
            int low = start;
            int high = end + 1;
            while (true) {
                while (low < end && data[++low] - base <= 0) ;
                while (high > start && data[--high] - base >= 0) ;
                if (low < high) {
                    // 交换data数组[low]与[high]位置的元素
                    swap(data, low, high);
                } else {
                    break;
                }
            }
            // 交换data数组[start]与[high]位置的元素
            swap(data, start, high);

            // 经过代码[start, high)部分的元素 比[high, end]都小

            // 通过递归调用，对data数组[start, high-1]部分的元素重复刚才的过程
            quickSort(data, start, high - 1);
            // 通过递归调用，对data数组[high+1,end]部分的元素重复刚才的过程
            quickSort(data, high + 1, end);
        }
    }

    public void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
