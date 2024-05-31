package com.cozz.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @date 2024/2/28
 **/
@Component
@ConfigurationProperties(prefix = "sc")
@Data
public class ConsulConfiguration {
    String info;
}
