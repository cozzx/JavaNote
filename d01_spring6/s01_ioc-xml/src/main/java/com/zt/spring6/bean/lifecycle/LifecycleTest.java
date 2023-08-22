package com.zt.spring6.bean.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 bean 声明周期
 *
 * @author zt
 * @since 2023/8/20 13:12
 **/
public class LifecycleTest {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-lifecycle.xml");
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        System.out.println("生命周期：4、通过IOC容器获取bean并使用");
        applicationContext.close();
    }
}
