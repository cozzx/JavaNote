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
@FeignClient(value = "sc-nacos-provider", fallbackFactory = NacosFeignFallbackFactory.class)
//@FeignClient(value = "cloud-gateway")
public interface NacosFeignApi {

    @PostMapping("/payment/create")
    Result create(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    Result getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/sentinel/get/{id}")
    Result testSentinelById(@PathVariable("id") Long id);

}
