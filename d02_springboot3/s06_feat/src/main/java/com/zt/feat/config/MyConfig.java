package com.zt.feat.config;

import com.zt.feat.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author zt
 * @since 2023/8/30 20:29
 **/
@Configuration
public class MyConfig {

    /**
     * profile 配置方式二
     */
    @Bean
    @Profile({"dev"})
    public Cat cat() {
        return new Cat();
    }
}
