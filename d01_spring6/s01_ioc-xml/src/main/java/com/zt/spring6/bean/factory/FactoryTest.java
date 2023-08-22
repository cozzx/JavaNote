package com.zt.spring6.bean.factory;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 factory 创建 bean
 *
 * @author zt
 * @since 2023/8/20 13:29
 **/
public class FactoryTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-factory.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println(user);
    }
}
