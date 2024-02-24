package com.cozz.starter.robot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性类
 *
 * @author zt
 * @date 2023/8/31
 **/
@Component
@Data
@ConfigurationProperties(prefix = "robot")
public class RobotProperties {
    private String name;
    private String age;
    private String email;
}
