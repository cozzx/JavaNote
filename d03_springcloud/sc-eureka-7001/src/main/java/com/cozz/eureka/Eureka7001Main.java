package com.cozz.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zt
 * @date 2024/2/23
 **/
@SpringBootApplication
@EnableEurekaServer
public class Eureka7001Main {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Main.class, args);
    }
}
