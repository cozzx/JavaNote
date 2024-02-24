package com.cozz.rpc.controller;

import com.cozz.rpc.service.ExpressApi;
import com.cozz.rpc.service.WeatherApi;
import com.cozz.rpc.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author zt
 * @date 2023/9/4
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
    public Mono<String> weatherWebClient(@PathVariable("city") String city) {
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
