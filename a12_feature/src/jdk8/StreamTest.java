package jdk8;

import common.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zt
 * @date 2023/6/28 07:16
 **/
public class StreamTest {

    public List<Employee> list = new ArrayList<>();

    public StreamTest() {
        list.add(new Employee(1, "老大", 44, 20000, true));
        list.add(new Employee(2, "老二", 35, 18764, true));
        list.add(new Employee(3, "老三", 32, 15674, true));
        list.add(new Employee(4, "老四", 28, 12985, false));
        list.add(new Employee(5, "老五", 24, 9875, false));
        list.add(new Employee(6, "老六", 21, 7456, false));
    }

    /**
     * 创建
     */
    @Test
    public void test1() {

        // 方式一：通过集合创建
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();
        Stream<Integer> parallelStream = list.parallelStream();
        System.out.println(stream);
        System.out.println(parallelStream);

        // 方式二：通过数组创建
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        Stream<Integer> stream1 = Arrays.stream(arr);
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        IntStream stream2 = Arrays.stream(arr1);
        System.out.println(stream1);
        System.out.println(stream2);

        // 方式三：Stream 的 of 静态方法，源码也是数组创建
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
        System.out.println(stream3);

        // 方式四：创建无限流
        // 迭代
        Stream<Integer> iterate = Stream.iterate(0, new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 2;
            }
        });
        iterate.limit(10).forEach(System.out::println);
        // 生成
        Stream<Double> generate = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });
        generate.limit(10).forEach(System.out::println);
    }

    /**
     * 中间操作测试
     * 筛选与切片
     */
    @Test
    public void test2() {

        // filter 条件过滤 判断型函数式接口
        list.stream().filter(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 30;
            }
        }).forEach(System.out::println);

        // limit 元素不超过N
        list.stream().limit(1).forEach(System.out::println);

        // skip 跳过N个元素
        list.stream().skip(4).forEach(System.out::println);

        // distinct 通过 hashCode() 和 equals() 去重
        list.add(new Employee(7, "老六", 21, 7456));
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * 中间操作测试
     * 映射
     */
    @Test
    public void test3() {

        // map
        list.stream().map(new Function<Employee, Double>() {
            @Override
            public Double apply(Employee employee) {
                return employee.getSalary() + 10000;
            }
        }).forEach(System.out::println);

        // peek
        list.stream().peek(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                employee.setSalary(employee.getSalary() + 10000);
            }
        }).forEach(System.out::println);

        // mapToDouble
        list.stream().mapToDouble(new ToDoubleFunction<Employee>() {
            @Override
            public double applyAsDouble(Employee employee) {
                return employee.getSalary();
            }
        }).forEach(System.out::println);

        // mapToInt
        list.stream().mapToInt(new ToIntFunction<Employee>() {
            @Override
            public int applyAsInt(Employee employee) {
                return employee.getAge();
            }
        }).forEach(System.out::println);
    }

    /**
     * 中间操作测试
     * 排序
     */
    @Test
    public void test4() {

        // sorted 默认比较器
        list.stream().sorted().forEach(System.out::println);

        // sorted(Comparator com) 自定义比较器
        list.stream().sorted(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o2.getAge() - o1.getAge();
            }
        }).forEach(System.out::println);
    }

    /**
     * 终止操作
     * 匹配与查找
     */
    @Test
    public void test5() {

        // allMatch 检查是否匹配所有元素
        System.out.println(list.stream().allMatch(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 30;
            }
        }));

        // anyMatch 检查是否有匹配的任意元素
        System.out.println(list.stream().anyMatch(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 30;
            }
        }));

        // noneMatch 检查是否有匹配的任意元素
        System.out.println(list.stream().noneMatch(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 50;
            }
        }));

        // findFirst 返回第一个元素
        System.out.println(list.stream().findFirst());

        // findAny 返回任意一个元素
        System.out.println(list.stream().findAny());

        // count 返回元素总数
        System.out.println(list.stream().filter(employee -> employee.getAge() > 30).count());

        // max 返回流中的最大值
        System.out.println(list.stream().max(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        }));

        // min 返回流中的最小值
        System.out.println(list.stream().min(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        }));
    }

    /**
     * 终止操作
     * 规约
     */
    @Test
    public void test6() {

        // reduce 将流中元素反复结合起来，得到一个值
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(intList.stream().reduce(0, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }));

        System.out.println(list.stream().map(Employee::getSalary).reduce(0.0, new BinaryOperator<Double>() {

            @Override
            public Double apply(Double aDouble, Double aDouble2) {
                return aDouble + aDouble2;
            }
        }));

        System.out.println(list.stream().map(Employee::getSalary).reduce(new BinaryOperator<Double>() {

            @Override
            public Double apply(Double aDouble, Double aDouble2) {
                return aDouble + aDouble2;
            }
        }).get());
    }

    /**
     * 终止操作
     * 收集
     */
    @Test
    public void test7() {

        // collect 将流转换为其他形式，Collectors 实用类提供了静态方法
        // toList 把流中元素收集到List
        List<Employee> list1 = list.stream().filter(emp -> emp.getSalary() > 10000).collect(Collectors.toList());
        list1.forEach(System.out::println);
        System.out.println();

        // toSet 把流中元素收集到Set
        Set<Employee> list2 = list.stream().filter(emp -> emp.getSalary() > 10000).collect(Collectors.toSet());
        list2.forEach(System.out::println);
        System.out.println();

        // toCollection 把流中元素收集到创建的集合
        Collection<Employee> list3 = list.stream().filter(emp -> emp.getSalary() > 10000).collect(Collectors.toCollection(ArrayList::new));
        list3.forEach(System.out::println);
        System.out.println();

        // counting 计算流中元素的个数
        long count = list.stream().filter(emp -> emp.getSalary() > 10000).collect(Collectors.counting());
        System.out.println(count);
        System.out.println();

        // 对流中元素的整数属性求和
        int total = list.stream().filter(emp -> emp.getSalary() > 10000).collect(Collectors.summingInt(Employee::getAge));
        System.out.println(total);
        System.out.println();

        // averagingInt 计算流中元素Integer属性的平均值
        double avg = list.stream().filter(emp -> emp.getSalary() > 10000).collect(Collectors.averagingInt(Employee::getAge));
        System.out.println(avg);
        System.out.println();

        // summarizingInt 收集流中Integer属性的统计值。如：平均值
        double SummaryStat = list.stream().filter(emp -> emp.getSalary() > 10000).collect(Collectors.summarizingInt(Employee::getAge)).getAverage();
        System.out.println(SummaryStat);
        System.out.println();

        // joining 连接流中每个字符串
        String str = list.stream().map(Employee::getName).collect(Collectors.joining());
        System.out.println(str);
        System.out.println();

        // maxBy, minBy 根据比较器选择最大值、最小值
        Optional<Employee> max = list.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(max.get());
        Optional<Employee> min = list.stream().collect(Collectors.minBy(Comparator.comparingInt(Employee::getAge)));
        System.out.println(min.get());
        System.out.println();

        // reducing 从一个作为累加器的初始值开始，利用BinaryOperator与流中元素逐个结合，从而归约成单个值
        int total1 = list.stream().collect(Collectors.reducing(10000, Employee::getAge, Integer::sum));
        System.out.println(total1);
        System.out.println();

        // collectingAndThen 包裹另一个收集器，对其结果转换函数
        int how = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println(how);
        System.out.println();

        // 根据某属性值对流分组，属性为K，结果为V
        Map<Boolean, List<Employee>> map1 = list.stream().collect(Collectors.groupingBy(Employee::getMarried));
        System.out.println(map1);
        System.out.println();

        // partitioningBy 根据true或false进行分区
        Map<Boolean, List<Employee>> map2 = list.stream().collect(Collectors.partitioningBy(employee -> employee.getSalary() > 10000));
        System.out.println(map2);
    }

    /**
     * Java9 新增
     */
    @Test
    public void test8() {

        // ofNullable()
        // 报NullPointerException
//         System.out.println(Stream.of(null).count());

        // 不报异常，允许通过
        System.out.println(Stream.of("AA", "BB", null).count());

        // 不报异常，允许通过
        System.out.println(Arrays.asList("AA", null).stream().count());

        // ofNullable()：允许值为null
        System.out.println(Stream.ofNullable(null).count());
        System.out.println(Stream.ofNullable("hello world").count());
        System.out.println();

        // iterator()
        // 原来的控制终止方式：
        Stream.iterate(1,i -> i + 1).limit(10).forEach(System.out::println);

        // 现在的终止方式：
        Stream.iterate(1,i -> i <= 10,i -> i + 1).forEach(System.out::println);
    }
}
