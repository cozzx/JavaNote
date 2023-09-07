package com.zt.message.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author zt
 * @since 2023/9/5 22:52
 **/
@Configuration
public class AppKafkaConfig {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("thing")
                .partitions(1)
                .compact()
                .build();
    }
}
