package classload;

import classload.common.MyClassLoader;
import classload.common.MyClassLoader1;
import classload.common.User;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zt
 * @date 2023/12/30
 **/
public class ClassLoaderTest {
    @Test
    public void test() {
        User user = new User();
        System.out.println(user);
        Class<? extends User> aClass = user.getClass();
        System.out.println(aClass.getClassLoader());
    }

    /**
     * 自定义 ClassLoader
     */
    @Test
    public void test2() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        // 获取当前目录，并假设"classes"目录包含我们的.class文件。
        URL url = new File("/Users/zhangtao/Code/Java/JavaNote/out/production/j02_classload/common").toURI().toURL();
        MyClassLoader loader = new MyClassLoader(new URL[]{url});
        Class<?> clazz = Class.forName("common.User", true, loader); // 使用自定义类加载器加载User类。
        Object instance = clazz.getDeclaredConstructor(String.class).newInstance("John"); // 创建User实例。
        System.out.println(instance); // 打印User实例。
        System.out.println("User class was loaded by: " + clazz.getClassLoader()); // 打印User类的类加载器。
        Method helloMethod = clazz.getMethod("sayHello");
        helloMethod.invoke(instance);
    }

    /**
     * 自定义类加载器 继承 ClassLoader
     */
    @Test
    public void test3() {
        MyClassLoader1 loader = new MyClassLoader1();
        try {
            // 加载并实例化自定义类
            Class<?> customClass = loader.loadClassFromFile("/Users/zhangtao/Code/Java/JavaNote/out/production/j02_classload/common/User.class");
            Object instance = customClass.getDeclaredConstructor().newInstance();

            ClassLoader classLoader = customClass.getClassLoader();
            System.out.println("ClassLoader: " + classLoader);

            customClass.getMethod("sayHello").invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
