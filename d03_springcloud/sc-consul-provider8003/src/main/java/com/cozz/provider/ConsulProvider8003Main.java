package com.cozz.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author zt
 * @date 2024/2/27
 **/
@SpringBootApplication
@RefreshScope // 自动刷新中心配置
public class ConsulProvider8003Main {
    public static void main(String[] args) {
        SpringApplication.run(ConsulProvider8003Main.class, args);
    }
}
