package com.cozz.consumer.controller;

import com.cozz.common.api.ConsulFeignApi;
import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author zt
 * @date 2024/2/27
 **/
@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    public static final String SERVICE_URL = "http://sc-nacos-provider";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/create")
    public Result create(Payment payment) {
        return restTemplate.postForObject(SERVICE_URL + "/payment/create", payment, Result.class);
    }

    @GetMapping("/get/{id}")
    public Result getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(SERVICE_URL + "/payment/get/" + id, Result.class);
    }
}
