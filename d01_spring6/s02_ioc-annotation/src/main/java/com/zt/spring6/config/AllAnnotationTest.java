package com.zt.spring6.config;

import com.zt.spring6.autowired.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 全注解开发
 *
 * @author zt
 * @since 2023/8/21 10:42
 **/
public class AllAnnotationTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Config.class);
        UserController userController = applicationContext.getBean("userController", UserController.class);
        userController.add();
    }
}
