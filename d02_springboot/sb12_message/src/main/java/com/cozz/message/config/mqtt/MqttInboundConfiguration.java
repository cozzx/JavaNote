package com.cozz.message.config.mqtt;

import com.cozz.message.handler.MqttReceiveMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * mqtt 消费者配置类
 *
 * @author zt
 * @date 2024/3/15
 **/
@Configuration
public class MqttInboundConfiguration {

    @Autowired
    MqttProperties mqttProperties;

    @Autowired
    MqttPahoClientFactory mqttPahoClientFactory;

    /**
     * MQTT信息通道
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 配置订阅客户端
     * 监听指定的MQTT主题并将接收到的消息推送到mqttInputChannel
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        mqttProperties.getClientId() + "_consumer",
                        mqttPahoClientFactory,
                        mqttProperties.getDefaultTopic().split(",")
                );
        adapter.setCompletionTimeout(mqttProperties.getCompletionTimeout());

        // Paho消息转换器, 按字节接收消息
//        DefaultPahoMessageConverter defaultPahoMessageConverter = new DefaultPahoMessageConverter();
//        defaultPahoMessageConverter.setPayloadAsBytes(true);
//        adapter.setConverter(defaultPahoMessageConverter);
        adapter.setQos(mqttProperties.getQos());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * MQTT消息处理器
     * ServiceActivator 注解表明：当前方法用于处理MQTT消息，inputChannel参数指定了用于消费消息的channel。
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MqttReceiveMessageHandler();
    }
}
