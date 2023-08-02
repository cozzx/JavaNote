package error;

import org.junit.Test;

/**
 * 常见的错误
 *
 * @author zt
 * @since 2023/7/19 20:35
 **/
public class ErrorTest {

    /**
     * 栈内存溢出错误 java.lang.StackOverflowError
     */
    @Test
    public void stackOverflowErrorTest() {
        recursion();
    }

    public void recursion() {
        recursion();
    }

    /**
     * 堆内存溢出错误 java.lang.OutOfMemoryError
     */
    @Test
    public void outOfMemoryErrorTest1() {
        int[] arr = new int[Integer.MAX_VALUE];
    }

    /**
     * 堆内存溢出错误 java.lang.OutOfMemoryError
     */
    @Test
    public void outOfMemoryErrorTest2() {
        StringBuilder s = new StringBuilder();
        while (true) {
            s.append("oom");
        }
    }
}
