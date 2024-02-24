package com.cozz.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. @MapperScan 告诉 mybatis 扫描哪个包下的接口
 * 2. 使用 mybatis.mapper-locations 告诉 mybatis 每个接口的xml文件的位置
 *
 * @author zt
 * @date 2023/8/30
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.cozz.ssm.mapper")
public class Sb05MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sb05MainApplication.class, args);
    }
}
