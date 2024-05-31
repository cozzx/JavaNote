package com.cozz.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2024/3/7
 **/
@Service
public class FlowLimitService {
    @SentinelResource(value = "common")
    public void common() {
        System.out.println("------FlowLimitService come in");
    }
}
