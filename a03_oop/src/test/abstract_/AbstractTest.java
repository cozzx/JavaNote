package test.abstract_;

import org.junit.Test;

/**
 * @author zt
 * @date 2023/6/27
 **/
public class AbstractTest {

    @Test
    public void test1() {
        Employee employee = new Employee(200);
        employee.eat();
        employee.breath();
    }

}
