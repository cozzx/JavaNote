package test.wrapper;

import org.junit.Test;

/**
 * @author zt
 * @since 2023/8/17 20:25
 **/
public class WrapperTest {

    @Test
    public void test1() {
        // 基本数据类型转包装类
        Integer i1 = Integer.valueOf(111);
        // 基本数据类型转包装类，自动装箱
        Integer i2 = 111;

        // 包装类转基本数据类型
        int i3 = i1.intValue();
        // 包装类转基本数据类型，自动拆箱
        int i4 = i1;

        // 字符串转包装类
        Integer i5 = Integer.valueOf("111");
        // 包装类转字符串
        String s5 = i5.toString();

        // 字符串转基本数据类型
        int i6 = Integer.parseInt("111");
        // 基本数据类型转字符串
        String s6 = String.valueOf(i6);
    }
}
