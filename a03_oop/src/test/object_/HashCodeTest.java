package test.object_;

import org.junit.Test;

/**
 * Object 类中的 hashCode(), 返回每个对象的 hash 值
 *
 * @author zt
 * @date 2024/1/11
 **/
public class HashCodeTest {

    @Test
    public void test() {
        System.out.println("AA".hashCode()); // 2080
        System.out.println("BB".hashCode()); // 2112
    }
}
