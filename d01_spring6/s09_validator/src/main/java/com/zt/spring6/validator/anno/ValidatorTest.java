package com.zt.spring6.validator.anno;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zt
 * @since 2023/8/22 20:36
 **/
public class ValidatorTest {

    @Test
    public void testJakarta() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ValidationConfig.class);
        ValidationJakarta validationBean = applicationContext.getBean(ValidationJakarta.class);
        User user = new User();
        user.setAge(-1);
        boolean validator = validationBean.validator(user);
        System.out.println(validator);
    }

    @Test
    public void testFramework() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ValidationConfig.class);
        ValidationFramework validationBean = applicationContext.getBean(ValidationFramework.class);
        User user = new User();
        user.setName("lucy");
        user.setAge(-2);
        boolean validator = validationBean.validator(user);
        System.out.println(validator);
    }
}
