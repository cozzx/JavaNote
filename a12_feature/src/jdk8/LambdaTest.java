package jdk8;

import common.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zt
 * @date 2023/6/27 12:39
 **/
public class LambdaTest {

    @Test
    public void test() {

        // 未使用Lambda表达式
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("未使用Lambda表达式");
            }
        };
        r1.run();

        // 使用Lambda表达式
        Runnable r2 = () -> System.out.println("使用Lambda表达式");
        r2.run();
    }

    @Test
    public void test1() {

        // 未使用Lambda表达式
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("未使用Lambda表达式");

        // 使用Lambda表达式
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("使用Lambda表达式");

        // 当 Lambda 体只有一条语句时，return 与大括号若有，都可以省略
        Consumer<String> con2 = (String s) -> System.out.println(s);
        con2.accept("使用Lambda表达式，省略括号");

        // 数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
        Consumer<String> con3 = (s) -> System.out.println(s);
        con3.accept("使用Lambda表达式，省略类型");

        // Lambda 若只需要一个参数时，参数的小括号可以省略
        Consumer<String> con4 = s -> System.out.println(s);
        con4.accept("使用Lambda表达式，省略参数括号");

        // Lambda 若只需要一个参数时，方法引用
        Consumer<String> con5 = System.out::println;
        con5.accept("使用Lambda表达式，方法引用");
    }

    @Test
    public void test2() {

        // 未使用Lambda表达式
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(com1.compare(12, 21));

        // 使用Lambda表达式
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(com2.compare(23, 21));

        // 使用Lambda表达式，方法引用
        Comparator<Integer> com3 = Integer::compare;
        System.out.println(com3.compare(23, 21));
    }

    /**
     * 方法引用测试，对象::实例方法
     */
    @Test
    public void test3() {

        Employee emp = new Employee(1001, "范德彪", 34, 6000.38);
        Supplier<String> sup1 = new Supplier<String>() {
            @Override
            public String get() {
                return emp.getName();
            }
        };
        System.out.println(sup1.get());

        // 使用Lambda表达式
        Supplier<String> sup2 = () -> emp.getName();
        System.out.println(sup2.get());

        // 使用方法引用
        Supplier<String> sup3 = emp::getName;
        System.out.println(sup3.get());
    }

    /**
     * 方法引用测试，类::静态方法
     */
    @Test
    public void test4() {

        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        // 使用Lambda表达式
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(com2.compare(23, 21));

        // 使用方法引用
        Comparator<Integer> com3 = Integer::compare;
        System.out.println(com3.compare(23, 21));
    }

    /**
     * 方法引用测试，类::实例方法
     */
    @Test
    public void test5() {

        Comparator<String> com1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare("abc", "abb"));

        // 使用Lambda表达式
        Comparator<String> com2 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com2.compare("abc", "abb"));

        // 使用方法引用
        Comparator<String> com3 = String::compareTo;
        System.out.println(com3.compare("abc", "abb"));
    }

    /**
     * 构造器引用测试
     */
    @Test
    public void test6() {

        Supplier<Employee> sup1 = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(sup1.get());

        // 使用Lambda表达式
        Supplier<Employee> sup2 = () -> new Employee();
        ;
        System.out.println(sup2.get());

        // 使用构造器引用
        Supplier<Employee> sup3 = Employee::new;
        ;
        System.out.println(sup3.get());


        Function<Integer, Employee> func1 = new Function<Integer, Employee>() {
            @Override
            public Employee apply(Integer id) {
                return new Employee(id);
            }
        };
        System.out.println(func1.apply(12));

        // 使用Lambda表达式
        Function<Integer, Employee> func2 = (id) -> new Employee(12);
        System.out.println(func2.apply(12));

        // 使用构造器引用
        Function<Integer, Employee> func3 = Employee::new;
        System.out.println(func3.apply(12));
    }

    /**
     * 数组引用测试
     */
    @Test
    public void test7() {

        Function<Integer, Employee[]> func1 = new Function<Integer, Employee[]>() {
            @Override
            public Employee[] apply(Integer length) {
                return new Employee[length];
            }
        };
        System.out.println(func1.apply(10).length);

        // 使用Lambda表达式
        Function<Integer, Employee[]> func2 = (Integer length) -> new Employee[length];
        System.out.println(func2.apply(10).length);

        // 使用数组引用
        Function<Integer, Employee[]> func3 = Employee[]::new;
        System.out.println(func3.apply(10).length);
    }
}
