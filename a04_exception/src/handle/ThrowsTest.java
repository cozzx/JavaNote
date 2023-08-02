package handle;

import org.junit.Test;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 异常处理 抛出 throws
 *
 * @author zt
 * @since 2023/7/19 23:00
 **/
public class ThrowsTest {

    @Test
    public void test1() {
        System.out.println("上课.....");
        try {
            afterClass();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("准备提前上课");
        }
        System.out.println("上课.....");
    }

    public static void afterClass() throws InterruptedException {
        for (int i = 10; i >= 1; i--) {
            Thread.sleep(1000);
            System.out.println("距离上课还有：" + i + "分钟");
        }
    }

    @Test
    public void test2() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("请输入第一个整数：");
            int a = input.nextInt();
            System.out.print("请输入第二个整数：");
            int b = input.nextInt();
            int result = divide(a, b);
            System.out.println(a + "/" + b + "=" + result);
        } catch (ArithmeticException e) {
            System.out.print("计算错误");
        } catch (InputMismatchException e) {
            System.out.print("输入错误");
        } finally {
            input.close();
        }
    }

    public int divide(int a, int b) throws ArithmeticException {
        return a / b;
    }

    @Test
    public void test3() {
        class Father {
            public void method() throws Exception {
                System.out.println("Father.method");
            }
        }
        class Son extends Father {
            @Override
            public void method() throws IOException, ClassCastException {
                System.out.println("Son.method");
            }
        }

        Son s = new Son();
        try {
            s.method();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
