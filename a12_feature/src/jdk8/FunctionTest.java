package jdk8;

import common.Employee;
import common.EmployeeService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * @author zt
 * @date 2023/6/27 12:50
 **/
public class FunctionTest {

    /**
     * 消费型接口
     */
    @Test
    public void testConsumer() {

        List<String> list = Arrays.asList("java", "go", "rust", "python");
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        list.forEach(s -> System.out.println(s));
        list.forEach(System.out::println);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "java");
        map.put(2, "go");
        map.put(3, "rust");
        map.put(4, "python");
        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer k, String v) {
                System.out.println(k + "-" + v);
            }
        });
        map.forEach((k, v) -> System.out.println(k + "-" + v));
        // 下面的调用错误，函数式接口中的抽象方法accept，与其内部实现时调用的对象的方法println的形参列表不一致
        // map.forEach(System.out::println(k + "-" + v));
    }

    /**
     * 供给型接口
     */
    @Test
    public void testSupplier() {

        Supplier<String> supplier1 = new Supplier<String>() {
            @Override
            public String get() {
                return "函数式";
            }
        };
        System.out.println(supplier1.get());

        Supplier<String> supplier2 = () -> "函数式";
        System.out.println(supplier2.get());


        Stream<Double> generate = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });
        generate.forEach(new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println(aDouble);
            }
        });

        Stream.generate(Math::random).forEach(System.out::println);
    }

    /**
     * 函数型接口
     */
    @Test
    public void testFunction() {

        HashMap<Integer, Employee> map = new HashMap<>();

        Employee e1 = new Employee(1, "老大", 44, 20000);
        Employee e2 = new Employee(2, "老二", 35, 18764);
        Employee e3 = new Employee(3, "老三", 32, 15674);
        Employee e4 = new Employee(4, "老四", 28, 12985);
        Employee e5 = new Employee(5, "老五", 24, 9875);
        Employee e6 = new Employee(6, "老六", 21, 7456);

        map.put(e1.getId(), e1);
        map.put(e2.getId(), e2);
        map.put(e3.getId(), e3);
        map.put(e4.getId(), e4);
        map.put(e5.getId(), e5);

        map.replaceAll(new BiFunction<Integer, Employee, Employee>() {
            @Override
            public Employee apply(Integer k, Employee v) {
                if (v.getSalary() < 10000) {
                    v.setSalary(10000);
                }
                return v;
            }
        });

        map.replaceAll((k, v) -> {
            if (v.getSalary() < 10000) {
                v.setSalary(10000);
            }
            return v;
        });

        map.forEach((k, v) -> System.out.println(k + "=" + v));


        Function<String, String> fun1 = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        };
        System.out.println(fun1.apply("hello"));

        Function<String, String> fun2 = s -> s.substring(0, 1).toUpperCase() + s.substring(1);
        System.out.println(fun2.apply("hello"));
    }

    /**
     * 判断型接口
     */
    @Test
    public void testPredicate() {

        // 下面的list没有remove方法，会报异常
        // Arrays.asList()方法转化成ArrayList后，方法返回的List对象不是我们常见的ArrayList，而是Arrays的一个内部类
        // 这个内部类没有重写 AbstractList 的 add 和 remove 方法
//        List<String> list = Arrays.asList("java", "go", "rust", "python");
        List<String> list = new ArrayList<>(Arrays.asList("java", "go", "rust", "python"));

        list.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() < 5;
            }
        });

        list.removeIf(s -> s.length() < 5);

        list.forEach(System.out::println);
    }

    /**
     * 判断型接口
     */
    @Test
    public void testPredicate2() {

        EmployeeService employeeService = new EmployeeService();
        employeeService.get(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 30;
            }
        }).forEach(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                System.out.println(employee);
            }
        });

        employeeService.get(employee -> employee.getSalary() > 10000).forEach(System.out::println);
    }
}
