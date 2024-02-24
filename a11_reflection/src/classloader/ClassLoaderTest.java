package classloader;

import common.Employee;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zt
 * @date 2023/6/29 18:50
 **/
public class ClassLoaderTest {

    /**
     * 三层类加载器
     */
    @Test
    public void test1() {

        // 获取系统类加载器
        ClassLoader classLoader1 = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader1);

        // 获取扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        // 获取引导类加载器：null
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);
    }

    /**
     * 不同类使用的加载器
     */
    @Test
    public void test2() throws ClassNotFoundException {

        // 用户自定义的类，使用的是系统类加载器加载的
        Class<Employee> employeeClass = Employee.class;
        ClassLoader classLoader = employeeClass.getClassLoader();
        System.out.println(classLoader);

        // 对于Java的核心api使用引导类加载器加载：null
        Class<?> stringClass = Class.forName("java.lang.String");
        ClassLoader classLoader1 = stringClass.getClassLoader();
        System.out.println(classLoader1);
    }

    /**
     * 使用 ClassLoader 获取流，加载指定的配置文件
     */
    @Test
    public void test3() throws IOException {

        Properties properties = new Properties();
        // 通过类加载器读取的文件默认路径为：当前 module 的 src 目录
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("info.properties");
        properties.load(resourceAsStream);

        String name = properties.getProperty("username");
        String pwd = properties.getProperty("password");
        System.out.println(name + ":" + pwd);
    }
}
