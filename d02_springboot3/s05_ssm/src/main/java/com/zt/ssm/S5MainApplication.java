package com.zt.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. @MapperScan 告诉 mybatis 扫描哪个包下的接口
 * 2. 使用 mybatis.mapper-locations 告诉 mybatis 每个接口的xml文件的位置
 *
 * @author zt
 * @since 2023/8/30 16:42
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.zt.ssm.mapper")
public class S5MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(S5MainApplication.class, args);
    }
}
