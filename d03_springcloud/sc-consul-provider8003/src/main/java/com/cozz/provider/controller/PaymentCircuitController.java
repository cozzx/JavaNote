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

import java.util.concurrent.TimeUnit;

/**
 * 测试断路器 CircuitBreaker
 *
 * @author zt
 * @date 2024/3/1
 **/
@Slf4j
@RequestMapping("payment")
@RestController
public class PaymentCircuitController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 测试 Resilience4j CircuitBreaker 服务
     */
    @GetMapping(value = "/circuit/get/{id}")
    public Result circuitGet(@PathVariable("id") Long id) {
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
                    .put("serverMethod", "circuitGet");
        } else {
            return Result.err()
                    .put("serverPort", serverPort)
                    .put("serverMethod", "circuitGet");
        }
    }

    /**
     * 测试 Resilience4j bulkhead 服务
     */
    @GetMapping(value = "/bulkhead/get/{id}")
    public Result bulkheadGet(@PathVariable("id") Long id) {
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
                    .put("serverMethod", "bulkheadGet");
        } else {
            return Result.err()
                    .put("serverPort", serverPort)
                    .put("serverMethod", "bulkheadGet");
        }
    }

    /**
     * 测试 Resilience4j ratelimiter 服务
     */
    @GetMapping(value = "/ratelimiter/get/{id}")
    public Result ratelimiterGet(@PathVariable("id") Long id) {
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
                    .put("serverMethod", "ratelimiterGet");
        } else {
            return Result.err()
                    .put("serverPort", serverPort)
                    .put("serverMethod", "ratelimiterGet");
        }
    }
}
