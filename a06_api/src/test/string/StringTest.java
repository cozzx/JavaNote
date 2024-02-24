package test.string;

import org.junit.Test;

/**
 * @author zt
 * @date 2023/6/21
 **/
public class StringTest {

    /**
     * 测试地址值是否相等
     */
    @Test
    public void test1() {
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1 == s2);
    }

    class Person {
        String name;
    }

    /**
     * 测试地址值是否相等
     */
    @Test
    public void test2() {
        Person p1 = new Person();
        p1.name = "Tom";

        Person p2 = new Person();
        p2.name = "Tom";

        System.out.println(p1.name.equals(p2.name));
        System.out.println(p1.name == p2.name);
        System.out.println(p1.name == "Tom");
    }

    /**
     * 通过 new 关键字创建字符串，测试地址值是否相等
     */
    @Test
    public void test3() {
        String s1 = "javaEE";
        String s2 = "javaEE";
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);
    }

    /**
     * 运算符参与后，字符串变量在内存的情况
     * 1. 常量+常量：结果是常量池，且常量池中不会存在相同内容的常量。编译期间确定结果。
     * 2. 常量与变量 或 变量与变量：结果在堆中
     * 3. 拼接后调用intern方法：返回值在常量池中
     */
    @Test
    public void test4() {
        String s1 = "hello";
        String s2 = "world";
        String s3 = "hello" + "world";
        String s4 = s1 + "world";
        String s5 = s1 + s2;
        String s6 = (s1 + s2).intern();

        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s4 == s5);
        System.out.println(s3 == s6);
    }

    /**
     * 使用final修饰的字符串变量，表示为常量，常量+常量：结果是常量池
     */
    @Test
    public void test5() {
        final String s1 = "hello";
        final String s2 = "world";
        String s3 = "hello" + "world";
        String s4 = s1 + "world";
        String s5 = s1 + s2;
        String s6 = (s1 + s2).intern();

        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s4 == s5);
        System.out.println(s3 == s6);
    }

    /**
     * concat方法拼接，结果在堆中
     */
    @Test
    public void test6() {
        String s1 = "helloworld";
        String s2 = "hello".concat("world");

        System.out.println(s1 == s2);
    }

    /**
     * str在change方法中重新赋值，相当于在常量池中获取新的地址值，不影响test方法中的str在常量池的地址值
     */
    @Test
    public void test7() {
        String str = "test";
        char[] ch = {'t', 'e', 's', 't'};
        change(str, ch);
        System.out.println(str);
        System.out.println(ch);
    }

    public void change(String str, char ch[]) {
        str = "best";
        ch[0] = 'b';
    }
}
