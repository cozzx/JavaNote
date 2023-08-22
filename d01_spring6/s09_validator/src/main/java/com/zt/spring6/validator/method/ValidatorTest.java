package com.zt.spring6.validator.method;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zt
 * @since 2023/8/22 20:36
 **/
public class ValidatorTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ValidationConfig.class);
        ValidationMethod validationBean = applicationContext.getBean(ValidationMethod.class);
        User user = new User();
        user.setName("lucy");
        user.setAge(-1);
        user.setPhone("135667543211");
        user.setMessage("  ");
        String str = validationBean.validator(user);
        System.out.println(str);
    }
}
