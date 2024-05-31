package com.cozz.provider.controller;

import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import com.cozz.provider.config.ConsulConfiguration;
import com.cozz.provider.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author zt
 * @date 2024/2/27
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

    @Value("${sc.info}")
    private String info;

    // 上面通过 @Value 方式读取 consul 中的配置, 热更新失败, 通过配置类可以热更新
    @Autowired
    ConsulConfiguration consulConfiguration;

    @GetMapping("get/{id}")
    public Result getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("获取结果：provider:" + payment);
        if (payment != null) {
            return Result.ok().data(payment)
                    .put("serverPort", serverPort)
                    .put("serverConfig", consulConfiguration.getInfo())
                    .put("serverValueConfig", info);
        } else {
            return Result.err()
                    .put("serverPort", serverPort)
                    .put("serverConfig", consulConfiguration.getInfo())
                    .put("serverValueConfig", info);
        }
    }

}
