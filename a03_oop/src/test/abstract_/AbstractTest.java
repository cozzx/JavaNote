package test.abstract_;

import org.junit.Test;

/**
 * @author zt
 * @since 2023/6/27 18:21
 **/
public class AbstractTest {

    @Test
    public void test1() {
        Employee employee = new Employee(200);
        employee.eat();
        employee.breath();
    }

}
