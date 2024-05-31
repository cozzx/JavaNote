package com.cozz.consumer.controller;

import com.cozz.common.api.ConsulFeignApi;
import com.cozz.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2024/2/27
 **/
@RestController
@Slf4j
@RequestMapping("feign-consumer")
public class OrderMicrometerController {

    @Autowired
    ConsulFeignApi consulFeignApi;

    /**
     * Micrometer(Sleuth)进行链路监控
     */
    @GetMapping(value = "/micrometer/get/{id}")
    public Result micrometer(@PathVariable("id") Long id) {
        return consulFeignApi.micrometerGet(id);
    }
}
