package common;

import java.util.Objects;

/**
 * @author zt
 * @date 2023/6/27 19:21
 **/
@TableTest("employee")
public class Employee implements Comparable<Employee> {

    @ColumnTest(columnName = "id", columnType = "int")
    private int id;

    @ColumnTest(columnName = "name", columnType = "varchar(255)")
    private String name;

    private int age;

    private double salary;

    private boolean married;

    public String pubStr;


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
                '}';
    }

    @Override
    public int compareTo(Employee e) {
        return this.age - e.getAge();
    }
}
