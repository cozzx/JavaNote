package com.cozz.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zt
 * @date 2024/2/28
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.cozz.common.api"}) // 启用feign客户端,定义服务+绑定接口，以声明式的方法优雅而简单的实现服务调用
public class ConsulConsumer8103Main {
    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumer8103Main.class, args);
    }
}
