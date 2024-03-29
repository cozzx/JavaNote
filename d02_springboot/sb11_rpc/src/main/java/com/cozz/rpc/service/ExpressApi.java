package com.cozz.rpc.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

/**
 * 快递查询接口
 *
 * @author zt
 * @date 2023/9/4
 **/
public interface ExpressApi {
    
    @GetExchange(url = "https://express3.market.alicloudapi.com/express3", accept = "application/json")
    Mono<String> getExpress(@RequestParam("number") String number);
}
