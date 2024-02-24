package test.feature;

import org.junit.Test;

/**
 * @author zt
 * @date 2023/6/27
 **/
public class FeatureTest {

    @Test
    public void test1() {
        Employee employee = new Employee(200);
        employee.eat();
        employee.sleep();
        employee.work();
        employee.setSalary(1000);
        employee.getSalary();
    }

    @Test
    public void test2() {
        People employee = new Employee();
        // 成员方法具备多态性, 这里调用的是子类方法中的 eat()
        employee.eat();
        // 以下报错，一个引用类型变量如果声明为父类的类型，但实际引用的是子类对象，那么该变量就不能再访问子类中添加的属性和方法。
//        employee.work();

        // 向下转型
        if (employee instanceof Employee) {
            Employee e = (Employee) employee;
            e.work();
        }
    }

    @Test
    public void test3() {
        People employee = new Employee();
        // 成员变量没有多态性
        System.out.println("employee.age = " + employee.age); // 0
    }
}
