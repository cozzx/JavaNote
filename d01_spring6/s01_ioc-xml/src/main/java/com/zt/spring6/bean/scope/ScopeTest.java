package com.zt.spring6.bean.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试bean作用域
 *
 * @author zt
 * @since 2023/8/20 13:01
 **/
public class ScopeTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-scope.xml");
        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        System.out.println(user1 == user2);
    }
}
