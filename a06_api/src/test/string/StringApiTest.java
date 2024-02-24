package test.string;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * String常用API
 *
 * @author zt
 * @date 2023/7/20
 **/
public class StringApiTest {

    /**
     * String构造器
     */
    @Test
    public void test1() throws UnsupportedEncodingException {

        // 字面量定义方式：字符串常量对象
        String str1 = "hello";
        System.out.println(str1);

        // 构造器定义方式：无参构造
        String str2 = new String();
        System.out.println(str2);

        // 构造器定义方式：创建"hello"字符串常量的副本
        String str3 = new String("hello");
        System.out.println(str3);
        System.out.println(str1 == str3);

        // 构造器定义方式：通过字符数组构造
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        String str4 = new String(chars);
        String str5 = new String(chars, 0, 3);
        System.out.println(str4);
        System.out.println(str5);
        System.out.println(str1 == str4);
        System.out.println(str3 == str4);

        // 构造器定义方式：通过字节数组构造
        byte[] bytes = {97, 98, 99};
        String str6 = new String(bytes);
        String str7 = new String(bytes, "GBK");
        System.out.println(str6);
        System.out.println(str7);
    }

    /**
     * 字符串 <-> 基本数据类型
     */
    @Test
    public void test2() {

        // 字符串 转 基本数据类型，使用各类型的包装类方法
        // 非数字字符串转换，或者超出要转换的类型范围，会抛出异常
        String str = "123";
        int i = Integer.parseInt(str);
        byte b = Byte.parseByte(str);
        short s = Short.parseShort(str);
        long l = Long.parseLong(str);
        float f = Float.parseFloat(str);
        double d = Double.parseDouble(str);
        System.out.println(i);
        System.out.println(b);
        System.out.println(s);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);

        // 基本数据类型 转 字符串，使用String类的valueOf方法
        String s1 = String.valueOf(i);
        String s2 = String.valueOf(b);
        String s3 = String.valueOf(s);
        String s4 = String.valueOf(l);
        String s5 = String.valueOf(f);
        String s6 = String.valueOf(d);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
    }

    /**
     * 字符串 <-> 字符数组
     */
    @Test
    public void test3() {

        // 字符串 转 字符数组
        String str1 = "hello java";
        char[] ca1 = str1.toCharArray();
        System.out.println(ca1);
        char[] ca2 = new char[5];
        str1.getChars(6, 8, ca2, 2);
        System.out.println(ca2);

        // 字符数组 转 字符串
        char[] ca3 = {'h', 'e', 'l', 'l', 'o', 'j', 'a', 'v', 'a'};
        String s1 = String.copyValueOf(ca3);
        String s2 = String.copyValueOf(ca3, 0, 5);
        String s3 = new String(ca3);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    /**
     * 字符串 <-> 字节数组
     */
    @Test
    public void test4() throws UnsupportedEncodingException {

        // 字符串 转 字节数组，编码
        String str1 = "abc我";
        byte[] b1 = str1.getBytes();
        for (byte b : b1) {
            System.out.println(b);
        }
        System.out.println();
        byte[] b2 = str1.getBytes("gbk");
        for (byte b : b2) {
            System.out.println(b);
        }
        System.out.println();

        // 字节数组 转 字符串，解码
        String s1 = new String(b1);
        System.out.println(s1);
        String s2= new String(b1, 2, 3);
        System.out.println(s2);
        System.out.println();

        String s3 = new String(b2, "gbk");
        System.out.println(s3);
        String s4= new String(b2, 2, 3, "gbk");
        System.out.println(s4);
    }
}
