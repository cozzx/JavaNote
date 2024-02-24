package bean.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试控制反转，对象创建交由 spring 控制
 *
 * @author zt
 * @date 2023/8/18
 **/
public class IOCTest {

    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        // 通过属性id获取bean
        HelloWorld helloworld1 = (HelloWorld) ac.getBean("helloWorld");
        helloworld1.sayHello();

        // 通过类型获取bean，当根据类型获取bean时，要求IOC容器中指定类型的bean有且只能有一个
        HelloWorld helloworld2 = ac.getBean(HelloWorld.class);
        helloworld2.sayHello();

        // 通过属性id和类型获取bean
        HelloWorld helloworld3 = ac.getBean("helloWorld", HelloWorld.class);
        helloworld3.sayHello();
    }
}
