package com.zt.boot.config;

import com.zt.boot.bean.Cat;
import com.zt.boot.bean.Dog;
import com.zt.boot.bean.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

/**
 * 配置类
 * 条件配置
 *
 * @author zt
 * @since 2023/8/22 22:09
 **/
@SpringBootConfiguration
public class AppConfigCondition {

    @ConditionalOnClass(name = "com.alibaba.druid.FastsqlException")
    @Bean
    public Cat cat01() {
        return new Cat();
    }

    @ConditionalOnMissingClass(value = "com.alibaba.druid.FastsqlException")
    @Bean
    public Dog dog01() {
        return new Dog();
    }

    /**
     * 有Dog类加载
     */
    @ConditionalOnBean(Dog.class)
    @Bean
    public User zhangsan() {
        return new User();
    }

    /**
     * 没有Dog类加载
     */
    @ConditionalOnMissingBean(Dog.class)
    @Bean
    public User lisi() {
        return new User();
    }
}
