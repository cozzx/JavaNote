package operator;

import org.junit.Test;

/**
 * 运算符测试
 *
 * @author zt
 * @since 2023/7/12 21:24
 **/
public class OperatorTest {

    /**
     * 加减乘除模
     */
    @Test
    public void test1() {

        System.out.println(5 + 2);
        System.out.println(5 - 2);
        System.out.println(5 * 2);
        System.out.println(5 / 2);
        System.out.println(5 % 2);

        // 取模运算结果与被模数符号相同
        System.out.println(5 / -2);
        System.out.println(5 % -2);
        System.out.println(-5 / -2);
        System.out.println(-5 % -2);
    }

    /**
     * 自增自减
     */
    @Test
    public void test2() {

        // 单独使用时，无论是变量前++和变量后++结果一样
        int a = 1;
        ++a;
        System.out.println(a);
        int b = 1;
        b++;
        System.out.println(b);

        // 其他变量放在一起使用
        int x = 1;
        int y = ++x;
        System.out.println(x);
        System.out.println(y);

        int m = 1;
        int n = m++;
        System.out.println(m);
        System.out.println(n);

        // 和输出语句一起
        int i = 1;
        System.out.println(++i);
        int j = 1;
        System.out.println(j++);

    }
}
