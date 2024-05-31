package com.cozz.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zt
 * @date 2024/3/10
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = "com.cozz.common.api")
public class SeataAccount6003Main {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccount6003Main.class, args);
    }
}
