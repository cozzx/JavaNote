package test.component;

import org.junit.Test;

import java.util.Arrays;

/**
 * 方法扩展
 *
 * @author zt
 * @date 2024/1/11
 **/
public class MethodExt {

    /**
     * 重载和可变参数
     */
    public void test() {
        System.out.println("test");
    }

    public void test(String a) {
        System.out.println("test1: " + a);
    }

    public void test(String a, String... b) {
        System.out.println("test2: " + a);
        System.out.println(Arrays.toString(b));
    }

    // 可变参数 和 同类型的数组是一致的, 不能重载
//    public void test(String a, String[] b) {
//        System.out.println("test3: " + a);
//        System.out.println(Arrays.toString(b));
//    }

    public void test(String a, int... b) {
        System.out.println("test4: " + a);
        System.out.println(Arrays.toString(b));
    }

    /**
     * 递归
     */
    // 递归求和, 调用自身
    public int getSum(int num) {
        // num 为 1 时, 方法返回 1, 相当于是方法的出口, num 总有是 1 的情况
        if (num == 1) {
            return 1;
        }
        // num 不为 1 时, 方法返回 num +(num-1) 的累和 递归调用 getSum 方法
        return num + getSum(num - 1);
    }

    @Test
    public void recursionTest() {
        // 计算1~num的和，使用递归完成
        int num = 5;
        // 调用求和的方法
        int sum = getSum(num);
        System.out.println(sum);
    }

    public int fibonacci(int n) {
        if (n < 3) {
            return 1;
        }
        return fibonacci(n - 2) + fibonacci(n - 1);
    }

    @Test
    public void fibonacciTest() {
        System.out.println(fibonacci(160));
    }
}
