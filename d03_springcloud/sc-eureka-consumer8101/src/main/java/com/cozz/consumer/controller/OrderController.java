package com.cozz.consumer.controller;

import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zt
 * @date 2024/2/23
 **/
@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    // 使用 eureka 服务名称时必须使用 @LoadBalanced 注解, 赋予 RestTemplate 负载均衡的能力
    // 在 restTemplate 配置类中添加
    public static final String PAYMENT_URL = "http://SC-EUREKA-PROVIDER";

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public Result create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, Result.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public Result getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, Result.class);
    }
}
