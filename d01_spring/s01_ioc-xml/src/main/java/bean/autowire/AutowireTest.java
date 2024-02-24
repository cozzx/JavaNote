package bean.autowire;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 autowire 自动装配
 *
 * @author zt
 * @date 2023/8/20
 **/
public class AutowireTest {
    /**
     * byType
     */
    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowire.xml");
        UserController userController = applicationContext.getBean(UserController.class);
        userController.saveUser();
    }

    /**
     * byName
     */
    @Test
    public void test2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowire-byname.xml");
        UserController userController = applicationContext.getBean(UserController.class);
        userController.saveUser();
    }
}
