package jdk8;

import org.junit.Test;

/**
 * @author zt
 * @since 2023/6/27 19:33
 **/
public class FunctionInterfaceImplTest {

    @Test
    public void test() {
        FunctionInterfaceTest fit = new FunctionInterfaceTest() {
            @Override
            public void method() {
                System.out.println("hello function interface");
            }
        };
        fit.method();
    }

    @Test
    public void test2() {
        FunctionInterfaceTest fit = () -> System.out.println("hello function interface lambda");
        fit.method();
    }
}
