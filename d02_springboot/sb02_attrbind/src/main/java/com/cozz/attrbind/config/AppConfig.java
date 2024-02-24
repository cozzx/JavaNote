package com.cozz.attrbind.config;

import com.cozz.attrbind.bean.Dog;
import com.cozz.attrbind.bean.Pig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author zt
 * @date 2023/8/28 20:05
 **/
@EnableConfigurationProperties(Pig.class) // 属性绑定方式三: 配置 @ConfigurationProperties 类注解
@SpringBootConfiguration
public class AppConfig {

    /**
     * 属性绑定方式二
     * 注解 @ConfigurationProperties 配置在方法中
     */
    @Bean
    @ConfigurationProperties(prefix = "dog")
    public Dog dog() {
        return new Dog();
    }
}
