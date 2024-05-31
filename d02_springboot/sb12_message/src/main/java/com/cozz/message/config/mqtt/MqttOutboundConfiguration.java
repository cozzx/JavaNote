package com.cozz.message.config.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * mqtt 生产者配置类
 *
 * @author zt
 * @date 2024/3/15
 **/
@Configuration
public class MqttOutboundConfiguration {

    @Autowired
    MqttProperties mqttProperties;

    @Autowired
    MqttPahoClientFactory mqttPahoClientFactory;

    /**
     * MQTT 生产者 信息通道
     */
    @Bean
    public MessageChannel mqttOutputChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT 生产者 消息处理器
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutputChannel")
    public MessageHandler mqttOutbound() {
        // 在这里进行 mqttOutboundChannel 的相关设置
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(
                        mqttProperties.getClientId() + "_producer",
                        mqttPahoClientFactory
                );
        // 如果设置成 true，发送消息时将不会阻塞。
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(mqttProperties.getDefaultTopic());
        messageHandler.setDefaultQos(mqttProperties.getQos());
        // Paho消息转换器, 发送bytes数据
//        DefaultPahoMessageConverter defaultPahoMessageConverter = new DefaultPahoMessageConverter();
//        defaultPahoMessageConverter.setPayloadAsBytes(true);
//        messageHandler.setConverter(defaultPahoMessageConverter);
        return messageHandler;
    }
}
