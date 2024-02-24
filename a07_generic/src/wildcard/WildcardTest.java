package wildcard;

import common.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 通配符使用测试
 *
 * @author zt
 * @date 2023/7/5 21:27
 **/
public class WildcardTest {

    public void read(Collection<?> coll) {
        for (Object o : coll) {
            System.out.println(o);
        }
    }

    public void write(Object o) {
        Collection<?> col = new ArrayList<>();
        // 编译时错误, 不知道 col 的类型，不能添加元素，不能用在创建对象上
//        col.add(o);
    }

    /**
     * 通配符读写测试
     */
    @Test
    public void test1() {
        read(new ArrayList<>(Arrays.asList("aa", "bb", "cc")));
        write("aa");

        List<Employee> employeeList = new ArrayList<>();
        List<? extends Employee> employee2List = null;
        employeeList.add(new Employee());
        employee2List = employeeList;

        // 读取：可以读
        Employee employee = employee2List.get(0);
        // 写入：除了null之外，不能写入
//        employee2List.add(new Employee());
        employee2List.add(null);
    }

    // 泛型的上限：此时的泛型?，必须是Number类型或者Number类型的子类
    public void getElement1(Collection<? extends Number> coll) {
    }

    // 泛型的下限：此时的泛型?，必须是Number类型或者Number类型的父类
    public void getElement2(Collection<? super Number> coll) {
    }

    /**
     * 有限制的通配符
     */
    @Test
    public void test2() {
        Collection<Integer> list1 = new ArrayList<>();
        Collection<String> list2 = new ArrayList<>();
        Collection<Number> list3 = new ArrayList<>();
        Collection<Object> list4 = new ArrayList<>();

        getElement1(list1);
//        getElement1(list2); //报错
        getElement1(list3);
//        getElement1(list4); //报错
//        getElement2(list1); //报错
//        getElement2(list2); //报错
        getElement2(list3);
        getElement2(list4);
    }

}
