package com.zt.web.config;

import com.zt.web.component.YamlHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author zt
 * @since 2023/8/29 09:43
 **/
@Configuration
public class HttpMessageConvertersConfig implements WebMvcConfigurer {

    /**
     * 引入一个自定义的 MessageConverters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
        converters.add(new YamlHttpMessageConverter());
    }
}
