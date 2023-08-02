package conversion;

import org.junit.Test;

/**
 * 基本数据类型转换
 *
 * @author zt
 * @since 2023/7/12 20:17
 **/
public class BasicDataTypeTest {

    /**
     * 自动类型提升1
     * 当把存储范围小的值（常量值、变量的值、表达式计算的结果值）赋值给了存储范围大的变量时，会自动类型提升
     */
    @Test
    public void test1() {

        // char 自动升级为 int，把字符的编码值赋值给 i 变量
        int i = 'A';
        System.out.println(i);

        // int 自动升级为 double
        double d = 10;
        System.out.println(d);

        // 错误，右边的整数常量值超过 byte 范围
//         byte bigB = 130;

        // 右边的整数常量值如果在 int 范围呢，编译和运行都可以通过，这里涉及到数据类型转换
        long num = 1234567;
        System.out.println(num);

        // 右边的整数常量值如果超过 int 范围，必须加L，显式表示long类型。否则编译不通过
//        long bigNum = 12345678912;
        long bigNum = 12345678912L;
        System.out.println(bigNum);
    }

    /**
     * 自动类型提升2
     * 当存储范围小的数据类型与存储范围大的数据类型变量一起混合运算时，会按照其中最大的类型运算
     */
    @Test
    public void test2() {

        byte b = 1;
        short s = 10;
        int i = 1;
        long l = 10000000000L;
        float f = 0.35F;
        double d = 1.0;
        char c = 'a';

        // 混合运算，升级为double
        double sum = b + s + i + l + f + d + c;
        System.out.println(sum);
    }

    /**
     * 自动类型提升3
     * 当 byte, short, char 数据类型的变量进行算术运算时，按照 int 类型处理。
     */
    @Test
    public void test3() {

        byte b1 = 1;
        byte b2 = 2;
        // 编译报错，b1 + b2自动升级为int
//        byte b3 = b1 + b2;

        char c1 = '0';
        char c2 = 'A';
        // 至少需要使用int类型来接收
        int i = c1 + c2;
        System.out.println(c1 + c2);
    }

    /**
     * 强制类型转换1
     * 当把存储范围大的值（常量值、变量的值、表达式计算的结果值）强制转换为存储范围小的变量时，可能会`损失精度`或`溢出`。
     */
    @Test
    public void test4() {

        // 损失精度
        int i = (int) 3.14;
        System.out.println(i);

        // 溢出
        int j = 200;
        byte b = (byte) j;
        System.out.println(b);
    }

    /**
     * 强制类型转换2
     * 当某个值想要提升数据类型时，也可以使用强制类型转换。这种情况的强制类型转换是`没有风险`的，通常省略。
     */
    @Test
    public void test5() {

        int i = 1;
        int j = 2;
        double sh = (double) i / j;
        System.out.println(sh);
    }

    /**
     * 强制类型转换3
     * 声明 long 类型变量时，可以出现省略后缀的情况。float 则不同。
     */
    @Test
    public void test6() {

        // 此时可以看做是int类型的123自动类型提升为long类型
        long l = 123;
        System.out.println(l);

        // 报错，因为12.3看做是double，不能自动转换为float类型
//        float f1 = 12.3;
        float f2 = 12.3F;
        float f3 = (float) 12.3;
        System.out.println(f2);
        System.out.println(f3);
    }
}
