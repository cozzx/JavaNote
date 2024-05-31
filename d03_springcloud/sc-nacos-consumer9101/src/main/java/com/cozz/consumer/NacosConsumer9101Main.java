package com.cozz.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zt
 * @date 2024/3/6
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.cozz.common.api"})
public class NacosConsumer9101Main {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumer9101Main.class, args);
    }
}
