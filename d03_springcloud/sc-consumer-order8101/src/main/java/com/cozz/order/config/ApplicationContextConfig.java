package com.cozz.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zt
 * @date 2024/2/23
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced // 使用 eureka 服务名称时必须使用 @LoadBalanced 注解, 赋予 RestTemplate 负载均衡的能力(ribbon)
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
