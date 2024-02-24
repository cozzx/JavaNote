package anno.config;

import anno.autowired.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 全注解开发
 *
 * @author zt
 * @date 2023/8/21
 **/
public class AllAnnotationTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Config.class);
        UserController userController = applicationContext.getBean("userController", UserController.class);
        userController.add();
    }
}
