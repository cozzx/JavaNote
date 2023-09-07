package com.zt.attrbind.config;

import com.zt.attrbind.bean.Dog;
import com.zt.attrbind.bean.Sheep;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author zt
 * @since 2023/8/28 20:05
 **/
@SpringBootConfiguration
@EnableConfigurationProperties(Dog.class)
public class AppConfig {

    /**
     * 属性绑定方式二
     * 注解 @ConfigurationProperties 配置在方法中
     */
    @Bean
    @ConfigurationProperties(prefix = "sheep")
    public Sheep sheep() {
        return new Sheep();
    }
}
