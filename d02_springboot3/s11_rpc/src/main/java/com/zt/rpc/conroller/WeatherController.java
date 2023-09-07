package com.zt.rpc.conroller;

import com.zt.rpc.service.ExpressApi;
import com.zt.rpc.service.WeatherApi;
import com.zt.rpc.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 执行远程调用
 *
 * @author zt
 * @since 2023/9/4 20:41
 **/
@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherApi weatherApi;

    @Autowired
    ExpressApi expressApi;

    /**
     * WebClient 方式
     */
    @GetMapping("/weatherWebClient/{city}")
    public String weatherWebClient(@PathVariable("city") String city) {
        return weatherService.getByWebClient(city);
    }

    /**
     * http interface 方式
     */
    @GetMapping("/weatherHttpInterface/{city}")
    public Mono<String> weatherHttpInterface(@PathVariable("city") String city) {
        return weatherApi.getWeather(city);
    }

    @GetMapping("/express")
    public Mono<String> express(@RequestParam("number") String number) {
        return expressApi.getExpress(number);
    }
}
