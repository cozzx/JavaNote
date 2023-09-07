package com.zt.start.robot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性类
 *
 * @author zt
 * @since 2023/8/31 22:19
 **/
@Component
@Data
@ConfigurationProperties(prefix = "robot") //此属性类和配置文件指定前缀绑定
public class RobotProperties {
    private String name;
    private String age;
    private String email;
}
