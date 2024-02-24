package com.cozz.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * 通过代码设置静态资源配置
 *
 * @author zt
 * @date 2023/8/29
 **/
@Configuration
public class ResourceConfig2 {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                WebMvcConfigurer.super.addResourceHandlers(registry);

                // 自定义
                registry.addResourceHandler("/code/**")
                        .addResourceLocations("classpath:/c/")
                        .setCacheControl(CacheControl.maxAge(1190, TimeUnit.SECONDS));
            }
        };
    }
}
