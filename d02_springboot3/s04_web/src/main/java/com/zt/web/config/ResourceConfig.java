package com.zt.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * 通过代码设置静态资源配置
 *
 * @author zt
 * @since 2023/8/29 07:04
 **/
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 保留 default
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // 自定义
        registry.addResourceHandler("/code/**")
                .addResourceLocations("classpath:/b/")
                .setCacheControl(CacheControl.maxAge(1180, TimeUnit.SECONDS));
    }
}
