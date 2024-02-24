package bean.factory;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 factory 创建 bean
 *
 * @author zt
 * @date 2023/8/20
 **/
public class FactoryTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-factory.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println(user);
    }
}
