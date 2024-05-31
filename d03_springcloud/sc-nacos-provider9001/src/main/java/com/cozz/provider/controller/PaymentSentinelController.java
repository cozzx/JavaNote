package com.cozz.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author zt
 * @date 2024/3/3
 **/
@Slf4j
@RequestMapping("sentinel")
@RestController
public class PaymentSentinelController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("get/{id}")
    @SentinelResource(value = "getPaymentById", blockHandler = "handlerBlockHandler"/*, fallback = "myFallBack"*/)
    public Result testSentinelById(@PathVariable("id") Long id) throws Exception {
        if (id <= 0) {
            throw new Exception("id 不能小于 0");
        }
        return Result.ok().put("serverPort", serverPort);
    }

    public Result handlerBlockHandler(@PathVariable("id") Long id, BlockException exception) {
        return Result.err("getPaymentById 服务不可用，触发sentinel流控配置规则 o(╥﹏╥)o");
    }

    /**
     * fallback服务降级方法纳入到Feign接口统一处理，全局一个
     */
//    public Result myFallBack(@PathVariable("id") Long id, Throwable throwable) {
//        return Result.err("异常情况：" + throwable.getMessage());
//    }

}
