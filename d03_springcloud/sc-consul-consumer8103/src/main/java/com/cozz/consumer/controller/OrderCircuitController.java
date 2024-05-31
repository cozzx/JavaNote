package com.cozz.consumer.controller;

import com.cozz.common.api.ConsulFeignApi;
import com.cozz.common.util.Result;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author zt
 * @date 2024/3/5
 **/
@RestController
@Slf4j
@RequestMapping("feign-consumer")
public class OrderCircuitController {

    @Autowired
    ConsulFeignApi consulFeignApi;

    /**
     * 测试 circuit 熔断降级
     */
    @GetMapping("/circuit/get/{id}")
    @CircuitBreaker(name = "sc-consul-provider", fallbackMethod = "circuitFallback")
    public Result circuitBreaker(@PathVariable("id") Long id) {
        return consulFeignApi.circuitGet(id);
    }

    /**
     * 服务降级后的兜底处理
     */
    public Result circuitFallback(Long id, Throwable t) {
        return Result.err("系统繁忙不稳定，请稍后再试");
    }

    /**
     * 测试 bulkhead 仓壁隔离, 信号量仓壁
     */
    @GetMapping("/semaphore-bulkhead/get/{id}")
    @Bulkhead(name="sc-consul-provider", fallbackMethod = "semaphoreBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public Result semaphoreBulkhead(@PathVariable("id") Long id) {
        return consulFeignApi.bulkheadGet(id);
    }

    public Result semaphoreBulkheadFallback(Long id, Throwable t) {
        return Result.err("semaphore提示: 系统繁忙阻塞，请稍后再试");
    }

    /**
     * 测试 bulkhead 仓壁隔离, 线程池仓壁
     */
    @GetMapping("/threadpool-bulkhead/get/{id}")
    @Bulkhead(name="sc-consul-provider", fallbackMethod = "threadpoolBulkheadFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<Result> threadpoolBulkhead(@PathVariable("id") Long id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "---开始进入");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "---准备离开");
        return CompletableFuture.supplyAsync(() -> consulFeignApi.bulkheadGet(id));
    }

    public CompletableFuture<Result> threadpoolBulkheadFallback(Long id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> Result.err("threadpool提示: 系统繁忙阻塞，请稍后再试"));
    }

    /**
     * 测试 ratelimiter 限流
     */
    @GetMapping(value = "/ratelimiter/get/{id}")
    @RateLimiter(name = "sc-consul-provider", fallbackMethod = "ratelimiterFallback")
    public Result myBulkhead(@PathVariable("id") Long id) {
        return consulFeignApi.ratelimiterGet(id);
    }

    public Result ratelimiterFallback(Long id, Throwable t) {
        return Result.err("系统繁忙限流中，请稍后再试");
    }
}

