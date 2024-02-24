package com.cozz.rpc.config;

import com.cozz.rpc.service.ExpressApi;
import com.cozz.rpc.service.WeatherApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author zt
 * @date 2023/9/4
 **/
@Configuration
public class AliyunApiConfig {

    @Bean
    HttpServiceProxyFactory httpServiceProxyFactory(@Value("${aliyun.appcode}") String appCode) {
        // 1. 创建客户端
        WebClient webClient = WebClient.builder()
                .defaultHeader("Authorization", "APPCODE " + appCode)
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(256 * 1024 * 1024))
                .build();

        // 2. 创建工厂
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return factory;
    }

    @Bean
    WeatherApi weatherApi(HttpServiceProxyFactory httpServiceProxyFactory) {
        // 3. 获取代理对象
        WeatherApi weatherInterface = httpServiceProxyFactory.createClient(WeatherApi.class);
        return weatherInterface;
    }

    @Bean
    ExpressApi expressApi(HttpServiceProxyFactory httpServiceProxyFactory) {
        ExpressApi client = httpServiceProxyFactory.createClient(ExpressApi.class);
        return client;
    }
}
