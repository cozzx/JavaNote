package customize;

import common.Employee;
import org.junit.Test;

import java.util.List;

/**
 * 自定义泛型类测试
 *
 * @author zt
 * @since 2023/7/5 20:15
 **/
public class GenericTest<T> {

    @Test
    public void test1() {
        GenericCustom<Employee> employeeGenericCustom = new GenericCustom<>();
        employeeGenericCustom.save("1001", new Employee(1001, "aa", 22, 20000));
        employeeGenericCustom.save("1002", new Employee(1002, "bb", 33, 30000));
        employeeGenericCustom.save("1003", new Employee(1003, "cc", 44, 40000));

        employeeGenericCustom.update("1002", new Employee(1002, "dd", 33, 30000));
        employeeGenericCustom.delete("1003");

        List<Employee> employeeList = employeeGenericCustom.list();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
