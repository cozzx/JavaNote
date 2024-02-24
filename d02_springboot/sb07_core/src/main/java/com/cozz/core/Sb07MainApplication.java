package com.cozz.core;

import com.cozz.starter.robot.controller.RobotController;
import com.cozz.starter.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zt
 * @date 2023/8/31
 **/
@SpringBootApplication
//@Import({RobotAutoConfiguration.class}) // 测试sb08自定义starter，使用方式一：引入配置类
//@EnableRobot // 测试sb08自定义starter，使用方式二：标注注解
// 测试sb08自定义starter，使用方式三：自动配置，无需标注任何配置，项目启动自动加载自动配置类
public class Sb07MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sb07MainApplication.class, args);
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
