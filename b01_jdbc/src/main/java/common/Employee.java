package main.java.common;

/**
 * 公用测试类
 *
 * @author zt
 * @date 2023/6/27
 **/
public class Employee {

    private int id;

    private String name;

    private int age;

    private double salary;

    private boolean married;


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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", married=" + married +
                '}';
    }
}
