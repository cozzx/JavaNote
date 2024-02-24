package com.cozz.boot;

import com.cozz.boot.bean.Cat;
import com.cozz.boot.bean.Dog;
import com.cozz.boot.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * SpringBoot 只会扫描主程序所在的包及其下面的子包，自动的 component-scan 功能
 * 自定义包扫描路径 @SpringBootApplication(scanBasePackages = "com.zt.boot.test")
 *
 * @author zt
 * @date 2023/8/22
 **/
@SpringBootApplication
public class Sb01MainApplication {
    public static void main(String[] args) {
        // java10 局部变量类型的自动推断
        var ioc = SpringApplication.run(Sb01MainApplication.class, args);
        // 获取容器中所有组件的名字
        var beanDefinitionNames = ioc.getBeanDefinitionNames();
        System.out.println("beanDefinitionNames.length = " + beanDefinitionNames.length);
        // SpringBoot 把以前配置的核心组件现在都给我们自动配置好了，dispatcherServlet、beanNameViewResolver、characterEncodingFilter
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);

        // 组件注册, @Scope("prototype") 修改为多实例
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
