package binary;

import org.junit.Test;

/**
 * 进制
 *
 * @author zt
 * @since 2023/7/12 21:13
 **/
public class BinaryTest {

    @Test
    public void test1() {

        int num1 = 123;
        int num2 = 0b101;
        int num3 = 0127;
        int num4 = 0x12aF;

        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        System.out.println(num4);
    }
}
