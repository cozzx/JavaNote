package com.cozz.provider.controller;

import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author zt
 * @date 2024/3/3
 **/
@Slf4j
@RequestMapping("payment")
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("create")
    public Result create(@RequestBody Payment payment) {
        return Result.ok().put("serverPort", serverPort);
    }

    @GetMapping("get/{id}")
    public Result getPaymentById(@PathVariable("id") Long id) {
        return Result.ok().put("serverPort", serverPort);
    }
}
