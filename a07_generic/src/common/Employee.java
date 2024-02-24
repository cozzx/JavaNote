package common;

import java.io.Serializable;
import java.util.Objects;

/**
 * 公共测试类
 *
 * @author zt
 * @date 2023/6/27 18:21
 **/
public class Employee implements Comparable<Employee>, Serializable {

    public static final long serialVersionUID = 12312321L;
    private int id;

    private String name;

    private int age;

    private double salary;

    private boolean married;

    public String pubStr;

    // static修饰的类变量，不会被序列化
    public static String ss = "123";

    // transient瞬态修饰成员,不会被序列化
    public transient int tt = 123;


    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    private Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.tt = 234;
    }

    public Employee(int id, String name, int age, double salary, boolean married) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.married = married;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean getMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    private void priMethod(String str) {
        System.out.println("This is a private method, and the obtained parameter is " + str);
    }

    private static void staMethod(String str) {
        System.out.println("This is a static method, and the obtained parameter is " + str);
    }

    private void showSomething() {
        System.out.println("go go go");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && Double.compare(employee.salary, salary) == 0 && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", married=" + married +
                ", pubStr='" + pubStr + '\'' +
                ", tt=" + tt +
                '}';
    }

    @Override
    public int compareTo(Employee e) {
        return this.age - e.getAge();
    }
}
