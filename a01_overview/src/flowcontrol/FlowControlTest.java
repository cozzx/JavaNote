package flowcontrol;

import org.junit.Test;

/**
 * 流程控制测试
 *
 * @author zt
 * @since 2023/7/12 21:50
 **/
public class FlowControlTest {

    /**
     * 99乘法表
     */
    @Test
    public void test1() {

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "*" + j + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 找质数
     */
    @Test
    public void test2() {

        boolean isFlag = true;
        for (int i = 2; i <= 100; i++) {
            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isFlag = false;
                }
            }
            if (isFlag) {
                System.out.println(i);
            }
            isFlag = true;
        }
    }

}
