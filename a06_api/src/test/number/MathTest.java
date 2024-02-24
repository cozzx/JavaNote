package test.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * 数值操作类
 *
 * @author zt
 * @date 2023/7/25
 **/
public class MathTest {

    /**
     * 绝对值
     */
    @Test
    public void test1() {
        int abs1 = Math.abs(-5);
        System.out.println(abs1); // 5
        double abs2 = Math.abs(-1.2);
        System.out.println(abs2); // 1.2
    }

    /**
     * 向上取整
     */
    @Test
    public void test2() {
        double d1 = Math.ceil(3.3);
        System.out.println(d1); // 4.0
        double d2 = Math.ceil(-3.3);
        System.out.println(d2); // -3.0
        double d3 = Math.ceil(5.1);
        System.out.println(d3); // 6.0
    }

    /**
     * 向下取整
     */
    @Test
    public void test3() {
        double d1 = Math.floor(3.3);
        System.out.println(d1); // 3.0
        double d2 = Math.floor(-3.3);
        System.out.println(d2); // -4.0
        double d3 = Math.floor(5.8);
        System.out.println(d3); // 5.0
    }

    /**
     * 四舍五入
     */
    @Test
    public void test4() {
        long l1 = Math.round(5.5);
        System.out.println(l1); // 6
        long l2 = Math.round(5.4);
        System.out.println(l2); // 5
        long l3 = Math.round(-3.3);
        System.out.println(l3); // -3
        long l4 = Math.round(-3.8);
        System.out.println(l4); // -4
    }

    /**
     * 其他
     */
    @Test
    public void test5() {
        // 2 的 31 次方
        double pow = Math.pow(2, 31);
        // 平方根
        double sqrt = Math.sqrt(256);
        // [0, 1) 的随机数
        double rand = Math.random();
        // 圆周率
        double pi = Math.PI;

        System.out.println(pow); //2.147483648E9
        System.out.println(sqrt); //16.0
        System.out.println(rand); //0.9131539222312002
        System.out.println(pi); //3.141592653589793
    }

    /**
     * java.math.BigInteger
     */
    @Test
    public void test6() {

        // 当数字超出 long 范围时，使用 BigInteger
        BigInteger b1 = new BigInteger("12345678912345678912345678");
        BigInteger b2 = new BigInteger("78923456789123456789123456789");

        // 错误，无法直接使用+进行求和
//        System.out.println("和：" + (b1 + b2));

        System.out.println("和：" + b1.add(b2));
        System.out.println("减：" + b1.subtract(b2));
        System.out.println("乘：" + b1.multiply(b2));
        System.out.println("除：" + b2.divide(b1));
        System.out.println("余：" + b2.remainder(b1));
    }

    /**
     * java.math.BigDecimal
     */
    @Test
    public void test7() {
        BigInteger bi = new BigInteger("12433241123");
        BigDecimal bd = new BigDecimal("12435.351");
        BigDecimal bd2 = new BigDecimal("11");
        System.out.println(bi);
        System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bd.divide(bd2, 15, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * java.util.Random
     */
    @Test
    public void test8() {
        Random r = new Random();
        System.out.println("随机整数：" + r.nextInt());
        System.out.println("[0, 10)的随机整数：" + r.nextInt(10));
        System.out.println("随机小数：" + r.nextDouble());
        System.out.println("随机布尔值：" + r.nextBoolean());
    }
}
