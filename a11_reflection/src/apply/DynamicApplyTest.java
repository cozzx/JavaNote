package apply;

import common.Employee;
import common.Fruit;
import common.Juicer;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author zt
 * @since 2023/6/29 18:46
 **/
public class DynamicApplyTest {
    
    /**
     * 动态获取实例
     */
    @Test
    public void test1() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Employee employee = getInstanceTest("common.Employee");
        System.out.println(employee);
    }

    public <T> T getInstanceTest(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        return (T) declaredConstructor.newInstance();
    }

    /**
     * 动态调用方法
     */
    @Test
    public void test2() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String info = (String) invoke("common.Employee", "showSomething");
        System.out.println("返回值为：" + info);
    }

    public Object invoke(String className, String methodName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object obj = constructor.newInstance();

        Method method = clazz.getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(obj);
    }

    /**
     * 通过配置文件，实现不同功能
     */
    @Test
    public void test3() throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        // 1 通过配置文件获取配置信息
        Properties properties = new Properties();
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
        properties.load(resourceAsStream);
        String fruitName = properties.getProperty("fruitName");

        // 2 通过配置信息加载对应的类
        Class<?> clazz = Class.forName(fruitName);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Fruit fruit = (Fruit) constructor.newInstance();

        // 3 调用类中的目标方法
        Juicer juicer = new Juicer();
        juicer.run(fruit);
    }
}
