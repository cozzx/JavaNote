package com.cozz.message.config.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

import java.nio.charset.StandardCharsets;

/**
 * mqtt 工厂类
 *
 * @author zt
 * @date 2024/3/15
 **/
@Configuration
public class MqttClientFactory {

    @Autowired
    MqttProperties mqttProperties;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttProperties.getUsername());
        mqttConnectOptions.setPassword(mqttProperties.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(mqttProperties.getHosts());
        // 设置超时时间 单位为秒
        mqttConnectOptions.setConnectionTimeout(mqttProperties.getConnectionTimeout());
        // 设置会话心跳时间 单位为秒 服务器会每隔*秒的时间向客户端发送心跳判断客户端是否在线, 但这个方法并没有重连的机制
        mqttConnectOptions.setKeepAliveInterval(mqttProperties.getKeepAlive());
        // 接受离线消息
        mqttConnectOptions.setCleanSession(false);
        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。
        mqttConnectOptions.setWill("willTopic", mqttProperties.getWillData().getBytes(StandardCharsets.UTF_8), 2, false);
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory defaultMqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        defaultMqttPahoClientFactory.setConnectionOptions(getMqttConnectOptions());
        return defaultMqttPahoClientFactory;
    }

}
