package com.zt.core;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zt
 * @since 2023/8/31 07:18
 **/
@SpringBootApplication
//@Import({RobotAutoConfiguration.class}) // 自定义starter，使用方式一：引入配置类
//@EnableRobot // 自定义starter，使用方式二：标注注解
// 自定义starter，使用方式三：自动配置，无需标注任何配置，项目启动自动加载自动配置类
public class S7MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(S7MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println("===ApplicationRunner 运行了.....");
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("===CommandLineRunner 运行了.....");
        };
    }
}
