package com.cozz.common.api;

import com.cozz.common.entity.CommonResult;
import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zt
 * @date 2024/2/28
 **/
@FeignClient(value = "sc-consul-provider")
public interface ConsulFeignApi {

    @PostMapping("/payment/create")
    Result create(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    Result getPaymentById(@PathVariable("id") Long id);

    /**
     * sc-consul-provider8003 Resilience4j CircuitBreaker 的接口
     */
    @GetMapping(value = "/payment/circuit/get/{id}")
    Result circuitGet(@PathVariable("id") Long id);

    /**
     * sc-consul-provider8003 Resilience4j bulkhead 的接口
     */
    @GetMapping(value = "/payment/bulkhead/get/{id}")
    Result bulkheadGet(@PathVariable("id") Long id);


    /**
     * sc-consul-provider8003 Resilience4j ratelimit 的接口
     */
    @GetMapping(value = "/payment/ratelimiter/get/{id}")
    Result ratelimiterGet(@PathVariable("id") Long id);

    /**
     * Micrometer(Sleuth) 进行链路监控的接口
     */
    @GetMapping(value = "/payment/micrometer/get/{id}")
    Result micrometerGet(@PathVariable("id") Long id);

    /**
     * GateWay 接口
     */
    @GetMapping(value = "/payment/gateway/get/{id}")
    Result getById(@PathVariable("id") Long id);

    /**
     * GateWay 接口
     */
    @GetMapping(value = "/payment/gateway/info")
    Result getInfo();
}
