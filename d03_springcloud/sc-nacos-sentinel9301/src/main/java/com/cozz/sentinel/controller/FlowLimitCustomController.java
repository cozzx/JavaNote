package com.cozz.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cozz.sentinel.service.FlowLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 按照 sentinelResource 资源名称限流
 * 自定义限流返回+降级处理
 *
 * @author zt
 * @date 2024/3/6
 **/
@RestController
@Slf4j
public class FlowLimitCustomController {

    /**
     * 自定义限流返回
     */
    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResourceSentinelResource", blockHandler = "handleException")
    public String byResource() {
        return "按资源名称SentinelResource限流测试OK";
    }

    public String handleException(BlockException exception) {
        return "服务不可用@SentinelResource启动" + "\t" + "o(╥﹏╥)o";
    }


    @GetMapping("/rateLimit/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource", blockHandler = "doActionBlockHandler", fallback = "doActionFallback")
    public String doAction(@PathVariable("p1") Integer p1) {
        if (p1 == 0){
            throw new RuntimeException("p1等于零直接异常");
        }
        return "doAction";
    }

    public String doActionBlockHandler(@PathVariable("p1") Integer p1, BlockException e){
        log.error("sentinel配置自定义限流了:{}", e);
        return "sentinel配置自定义限流了";
    }

    public String doActionFallback(@PathVariable("p1") Integer p1, Throwable e){
        log.error("程序逻辑异常了:{}", e);
        return "程序逻辑异常了"+"\t"+e.getMessage();
    }
}
