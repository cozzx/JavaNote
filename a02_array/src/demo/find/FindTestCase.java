package demo.find;

import org.junit.Test;

/**
 * 数组元素查找
 *
 * @author zt
 * @since 2023/8/2 13:00
 **/
public class FindTestCase {

    /**
     * 顺序查找
     */
    @Test
    public void test1() {
        int[] arr = {4, 5, 6, 1, 9};
        int value = 1;
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(value + "不存在");
        } else {
            System.out.println(value + "的下标是" + index);
        }
    }

    /**
     * 二分查找
     */
    @Test
    public void test2() {
        int[] arr = new int[]{-99, -54, -2, 0, 2, 33, 43, 256, 999};
        boolean isFlag = true;
        int value = 256;
        // 首索引位置
        int head = 0;
        // 尾索引位置
        int end = arr.length - 1;
        while (head <= end) {
            int middle = (head + end) / 2;
            if (arr[middle] > value) {
                end = middle - 1;
            } else if (arr[middle] < value){
                head = middle + 1;
            } else {
                System.out.println(value + "的下标是" + middle);
                isFlag = false;
                break;
            }
        }
        if (isFlag) {
            System.out.println(value + "不存在");
        }
    }
}
