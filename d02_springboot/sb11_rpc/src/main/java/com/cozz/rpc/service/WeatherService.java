package com.cozz.rpc.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zt
 * @date 2023/9/4
 **/
@Service
public class WeatherService {

    /**
     * WebClient 方式
     */
    public Mono<String> getByWebClient(String city) {
        // 远程调用阿里云 api
        // 1. 创建 WebClient
        WebClient webClient = WebClient.create();

        // 2. 定义请求行为
        Map<String, String> params = new HashMap<>();
        params.put("area", city);
        return webClient.get()
                .uri("https://ali-weather.showapi.com/area-to-weather-date?area={area}", params)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "APPCODE 93b7e19861a24c519a7548b17dc16d75")
                .retrieve()
                .bodyToMono(String.class);
    }
}
