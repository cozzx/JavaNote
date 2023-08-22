package com.zt.spring6.validator.method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 基于方法实现校验
 * 配置类
 *
 * @author zt
 * @since 2023/8/22 21:03
 **/
@Configuration
@ComponentScan("com.zt.spring6.validator.method")
public class ValidationConfig {

    @Bean
    public MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
