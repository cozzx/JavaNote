package com.cozz.boot.config;

import com.cozz.boot.bean.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 配置类
 * 注解 @SpringBootConfiguration 表示配置类，同 @Configuration，替代以前的配置文件
 * 配置类本身也是容器中的组件
 *
 * @author zt
 * @date 2023/8/22
 **/
@SpringBootConfiguration
public class AppConfig {

    /**
     * 组件注册
     * 组件默认是单实例的，通过 @Scope("prototype") 修改
     * 注解 @Bean 替代以前的 xml bean。组件在容器中的名字默认是方法名，可以直接修改注解的值。
     */
    @Scope("prototype")
    @Bean("userHaha")
    public User user01() {
        var user = new User();
        user.setId(1L);
        user.setName("张三");
        return user;
    }
}
