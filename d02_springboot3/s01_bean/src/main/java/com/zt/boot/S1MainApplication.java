package com.zt.boot;

import com.zt.boot.bean.Cat;
import com.zt.boot.bean.Dog;
import com.zt.boot.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 只会扫描主程序所在的包及其下面的子包，自动的 component-scan 功能
 * 自定义包扫描路径 @SpringBootApplication(scanBasePackages = "com.zt.boot.test")
 *
 * @author zt
 * @since 2023/8/22 15:17
 **/
@SpringBootApplication
public class S1MainApplication {
    public static void main(String[] args) {
        // java10 局部变量类型的自动推断
        var ioc = SpringApplication.run(S1MainApplication.class, args);
        // 获取容器中所有组件的名字
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        // SpringBoot 把以前配置的核心组件现在都给我们自动配置好了，dispatcherServlet、beanNameViewResolver、characterEncodingFilter
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        // 组件注册
        Object userHaha1 = ioc.getBean("userHaha");
        Object userHaha2 = ioc.getBean("userHaha");
        System.out.println(userHaha2 == userHaha1);

        // 组件条件注册
        for (String s : ioc.getBeanNamesForType(Cat.class)) {
            System.out.println("cat: " + s);
        }
        for (String s : ioc.getBeanNamesForType(Dog.class)) {
            System.out.println("dog: " + s);
        }
        for (String s : ioc.getBeanNamesForType(User.class)) {
            System.out.println("user: " + s);
        }

    }
}
