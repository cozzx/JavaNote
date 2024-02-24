package exception;

import org.junit.Test;

import java.util.Scanner;

/**
 * 常见的运行时异常
 *
 * @author zt
 * @date 2023/7/19
 **/
public class RuntimeExceptionTest {

    /**
     * 空指针异常 java.lang.NullPointerException
     */
    @Test
    public void test1() {
        int[][] arr = new int[3][];
        System.out.println(arr[0].length);
    }

    /**
     * 类转换异常 java.lang.ClassCastException
     */
    @Test
    public void test2() {
        Object obj = 15;
        String str = (String) obj;
    }

    /**
     * 数组越界异常 java.lang.ArrayIndexOutOfBoundsException
     */
    @Test
    public void test3() {
        int[] arr = new int[5];
        for (int i = 1; i <= 5; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 输入不匹配异常 java.util.InputMismatchException
     */
    @Test
    public void test4() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入一个整数：");
        int num = input.nextInt();
        input.close();
    }

    /**
     * 算数异常 java.lang.ArithmeticException
     */
    @Test
    public void test5() {
        int a = 1;
        int b = 0;
        System.out.println(a / b);
    }
}
