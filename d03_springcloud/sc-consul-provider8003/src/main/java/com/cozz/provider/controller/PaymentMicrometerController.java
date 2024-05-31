package com.cozz.provider.controller;

import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import com.cozz.provider.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zt
 * @date 2024/2/27
 **/
@Slf4j
@RequestMapping("payment")
@RestController
public class PaymentMicrometerController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * Micrometer(Sleuth)进行链路监控
     */
    @GetMapping(value = "/micrometer/get/{id}")
    public Result micrometerGet(@PathVariable("id") Long id) {
        if (id <= 0) {
            throw new RuntimeException("id 必须大于 0");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.error("exception message", e);
            }
        }
        Payment payment = paymentService.getPaymentById(id);
        log.info("获取结果：provider:" + payment);
        if (payment != null) {
            return Result.ok().data(payment)
                    .put("serverPort", serverPort)
                    .put("serverMethod", "micrometerGet");
        } else {
            return Result.err()
                    .put("serverPort", serverPort)
                    .put("serverMethod", "micrometerGet");
        }
    }
}
