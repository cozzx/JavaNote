package com.cozz.consumer.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

/**
 * @author zt
 * @date 2024/2/23
 **/
@Configuration
@LoadBalancerClient(value = "SC-EUREKA-PROVIDER", configuration = RestTemplateConfig.class)
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // 使用 eureka 服务名称时必须使用 @LoadBalanced 注解, 赋予 RestTemplate 负载均衡的能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 切换 LoadBalanced 算法, 从轮训切换为随机
     */
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
}
