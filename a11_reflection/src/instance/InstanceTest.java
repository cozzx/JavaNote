package instance;

import common.Employee;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zt
 * @date 2023/6/29 12:01
 **/
public class InstanceTest {

    /**
     * 不使用反射获取类实例，调用属性和方法
     */
    @Test
    public void test1() {

        // 1. 获取实例
        Employee employee = new Employee(1, "ref", 10, 1999.9);

        // 2.调用属性
        employee.pubStr = "ref";
        System.out.println(employee.pubStr);

        // 3.调用方法
        System.out.println(employee.getName());
    }

    /**
     * 使用反射获取类实例，调用属性和方法
     */
    @Test
    public void test2() throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        // 1. 获取实例
        Class<Employee> employeeClass = Employee.class;
        Employee employeeDeprecated = employeeClass.newInstance();
        System.out.println(employeeDeprecated);

        Constructor<Employee> constructor = employeeClass.getConstructor(int.class, String.class, int.class, double.class);
        Employee employee = constructor.newInstance(1, "ref", 21, 1999.9);
        System.out.println(employee);

        // 2.调用属性
        Field pubStrField = employeeClass.getField("pubStr");
        pubStrField.set(employee, "reflect");
        System.out.println(pubStrField.get(employee));

        // 3.调用方法
        Method showNameMethod = employeeClass.getMethod("setName", String.class);
        showNameMethod.invoke(employee, "reflection");
        System.out.println(employee);
    }

    /**
     * 使用反射，调用私有构造器、私有属性、私有方法
     */
    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // 1. 获取实例，调用私有构造器
        Class<Employee> employeeClass = Employee.class;
        Constructor<Employee> declaredConstructor = employeeClass.getDeclaredConstructor(int.class, String.class);
        declaredConstructor.setAccessible(true);
        Employee employee = declaredConstructor.newInstance(1, "ref");
        System.out.println(employee);

        // 2. 调用私有属性
        Field nameField = employeeClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(employee, "reflect");
        System.out.println(nameField.get(employee));

        // 3. 调用私有方法
        Method priMethod = employeeClass.getDeclaredMethod("priMethod", String.class);
        priMethod.setAccessible(true);
        priMethod.invoke(employee, "reflection");
        System.out.println(employee);

        // 3.1. 静态方法
        Method staMethod = employeeClass.getDeclaredMethod("staMethod", String.class);
        staMethod.setAccessible(true);
        staMethod.invoke(null, "reflection");
        System.out.println(employee);
    }

    /**
     * 四种获取类实例的方式
     */
    @Test
    public void test4() throws ClassNotFoundException {

        // 1. 调用运行时类的静态属性：class
        Class<Employee> employeeClass1 = Employee.class;
        System.out.println(employeeClass1);

        // 2. 调用运行时类的对象的方法：getClass()
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass2 = employee.getClass();
        System.out.println(employeeClass2);

        // 3. 调用 Class 的静态方法：forName(String className)
        Class<?> employeeClass3 = Class.forName("common.Employee");
        System.out.println(employeeClass3);

        // 4. 使用类的加载器方式
        Class<?> employeeClass4 = ClassLoader.getSystemClassLoader().loadClass("common.Employee");
        System.out.println(employeeClass4);


        // 类的 class 实例只有一个
        System.out.println(employeeClass1 == employeeClass2);
        System.out.println(employeeClass1 == employeeClass3);
        System.out.println(employeeClass1 == employeeClass4);

        // 只要元素类型与维度一样，就是同一个Class
//        Class<Object> objectClass = Object.class;
//        Class<String[]> stringClass = String[].class;
//        Class<int[]> intArrayClass = int[].class;
//        Class<int[][]> intArray2Class = int[][].class;
//        Class<Comparable> comparableClass = Comparable.class;
//        Class<Override> overrideClass = Override.class;
//        Class<Void> voidClass = void.class;
//        Class<Class> classClass = Class.class;
    }
}
