package anno.resource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zt
 * @date 2023/8/21
 **/
public class ResourceTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-resource.xml");
        UserController userController = applicationContext.getBean(UserController.class);
        userController.add();
    }
}
