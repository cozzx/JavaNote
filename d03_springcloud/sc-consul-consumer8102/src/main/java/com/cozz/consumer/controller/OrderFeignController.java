package com.cozz.consumer.controller;

import com.cozz.common.api.ConsulFeignApi;
import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zt
 * @date 2024/2/27
 **/
@RestController
@Slf4j
@RequestMapping("feign-consumer")
public class OrderFeignController {

    @Autowired
    ConsulFeignApi consulFeignApi;

    @GetMapping("/payment/get/{id}")
    public Result getPayment(@PathVariable("id") Long id) {
        return consulFeignApi.getPaymentById(id);
    }

    @PostMapping("/payment/create")
    public Result create(Payment payment) {
        return consulFeignApi.create(payment);
    }
}
