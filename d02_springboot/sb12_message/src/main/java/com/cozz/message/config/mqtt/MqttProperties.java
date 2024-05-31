package com.cozz.message.config.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @date 2024/3/15
 **/
@ConfigurationProperties("spring.mqtt")
@Component
@Data
public class MqttProperties {

    private String username;

    private String password;

    private String[] hosts;

    private String clientId;

    private String defaultTopic;

    private Long completionTimeout;

    private Integer connectionTimeout;

    private Integer keepAlive;

    private Integer qos;

    private String willData;
}
