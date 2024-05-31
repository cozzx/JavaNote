package com.cozz.provider.controller;

import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import com.cozz.provider.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zt
 * @date 2024/2/22
 **/
@Slf4j
@RequestMapping("payment")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("create")
    public Result create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);
        if (result > 0) {
            return Result.ok().put("serverPort", serverPort);
        } else {
            return Result.err().put("serverPort", serverPort);
        }
    }

    @GetMapping("get/{id}")
    public Result getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("获取结果：provider:" + payment);
        if (payment != null) {
            return Result.ok().data(payment).put("serverPort", serverPort);
        } else {
            return Result.err().put("serverPort", serverPort);
        }
    }

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("discovery")
    public DiscoveryClient discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(log::info);

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(instance -> log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri()));

        return this.discoveryClient;
    }
}
