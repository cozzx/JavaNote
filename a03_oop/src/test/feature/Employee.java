package test.feature;

/**
 * @author zt
 * @since 2023/6/27 18:21
 **/
public class Employee extends People {

    double salary;

    public Employee() {
        System.out.println("我是一名打工仔");
    }

    public Employee(double salary) {
        this.salary = salary;
        System.out.println("我是一名打工仔，初始工资是" + salary);
    }

    @Override
    public void eat() {
        System.out.println("吃工作餐");
    }

    @Override
    public void sleep() {
        System.out.println("加班睡不好觉");
    }

    public void work() {
        System.out.println("工作");
    }

    public double getSalary() {
        System.out.println("我的工资是" + salary);
        return salary;
    }

    public void setSalary(double salary) {
        System.out.println("我的工资变化了，现在是" + salary);
        this.salary = salary;
    }
}
