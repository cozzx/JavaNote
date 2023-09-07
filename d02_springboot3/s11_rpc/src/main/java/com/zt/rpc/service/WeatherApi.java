package com.zt.rpc.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

/**
 * 天气查询接口
 *
 * @author zt
 * @since 2023/9/4 22:46
 **/
public interface WeatherApi {

    @GetExchange(url = "https://ali-weather.showapi.com/area-to-weather-date", accept = "application/json")
    Mono<String> getWeather(@RequestParam("area") String city);
}
