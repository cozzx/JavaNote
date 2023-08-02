package demo.assign;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 数组赋值案例
 *
 * @author zt
 * @since 2023/8/2 07:56
 **/
public class AssignTestCase {

    /**
     * 创建一个长度为6的int型数组，要求数组元素的值都在1-10之间，且是随机赋值。同时，要求元素的值各不相同。
     */
    @Test
    public void test1() {
        int[] arr = new int[6];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(10) + 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    i--;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 扑克牌排序
     */
    @Test
    public void test2() {
        String[] hua = {"黑桃", "红桃", "梅花", "方片"};
        String[] dian = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] pai = new String[hua.length * dian.length];
        int k = 0;
        for (int i = 0; i < hua.length; i++) {
            for (int j = 0; j < dian.length; j++) {
                pai[k++] = hua[i] + dian[j];
            }
        }
        for (int i = 0; i < pai.length; i++) {
            System.out.print(pai[i] + "  ");
            if (i % 13 == 12) {
                System.out.println();
            }
        }
    }

    /**
     * 杨辉三角
     */
    @Test
    public void test3() {
        int[][] yangHui = new int[10][];
        for (int i = 0; i < yangHui.length; i++) {
            yangHui[i] = new int[i + 1];
            // 给数组每行的首末元素赋值为1
            yangHui[i][0] = yangHui[i][i] = 1;
            // 给数组每行的非首末元素赋值，j从每行的第2个元素开始，到倒数第2个元素结束
            for (int j = 1; j < yangHui[i].length - 1; j++) {
                yangHui[i][j] = yangHui[i - 1][j] + yangHui[i - 1][j - 1];
            }
        }

        for (int[] ints : yangHui) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 回形数排列
     */
    @Test
    public void test4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入一个数字");
        int len = scanner.nextInt();
        int[][] arr = new int[len][len];

        int s = len * len;
        /*
         * k = 1:向右
         * k = 2:向下
         * k = 3:向左
         * k = 4:向上
         */
        int k = 1;
        int i = 0, j = 0;
        for (int m = 1; m <= s; m++) {
            if (k == 1) {
                if (j < len && arr[i][j] == 0) {
                    arr[i][j++] = m;
                } else {
                    k = 2;
                    i++;
                    j--;
                    m--;
                }
            } else if (k == 2) {
                if (i < len && arr[i][j] == 0) {
                    arr[i++][j] = m;
                } else {
                    k = 3;
                    i--;
                    j--;
                    m--;
                }
            } else if (k == 3) {
                if (j >= 0 && arr[i][j] == 0) {
                    arr[i][j--] = m;
                } else {
                    k = 4;
                    i--;
                    j++;
                    m--;
                }
            } else if (k == 4) {
                if (i >= 0 && arr[i][j] == 0) {
                    arr[i--][j] = m;
                } else {
                    k = 1;
                    i++;
                    j++;
                    m--;
                }
            }
        }

        //遍历
        for (int m = 0; m < arr.length; m++) {
            for (int n = 0; n < arr[m].length; n++) {
                System.out.print(arr[m][n] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 回形针排列2
     */
    @Test
    public void test5() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入一个数字");
        int len = scanner.nextInt();
        int[][] arr = new int[len][len];

        int count = 0; //要显示的数据
        int maxX = len - 1; //x轴的最大下标
        int maxY = len - 1; //Y轴的最大下标
        int minX = 0; //x轴的最小下标
        int minY = 0; //Y轴的最小下标
        while (minX <= maxX) {
            for (int x = minX; x <= maxX; x++) {
                arr[minY][x] = ++count;
            }
            minY++;
            for (int y = minY; y <= maxY; y++) {
                arr[y][maxX] = ++count;
            }
            maxX--;
            for (int x = maxX; x >= minX; x--) {
                arr[maxY][x] = ++count;
            }
            maxY--;
            for (int y = maxY; y >= minY; y--) {
                arr[y][minX] = ++count;
            }
            minX++;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(String.format("%02d\t", arr[i][j]));
            }
            System.out.println();
        }
    }
}
