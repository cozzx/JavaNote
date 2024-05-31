package com.cozz.message.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2024/3/15
 **/
@Service
@MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
public interface MqttGateWayService {

    void sendMessageToMqtt(String data);

    // The topic name MUST NOT contain any wildcard characters (#+)
    void sendMessageToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

    void sendMessageToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos);
}
