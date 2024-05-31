package com.cozz.message.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * @author zt
 * @date 2024/3/15
 **/
public class MqttReceiveMessageHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        // 处理从MQTT接收到的消息
        String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
        Integer qos = (Integer) message.getHeaders().get("mqtt_receivedQos");
        String payload = String.valueOf(message.getPayload());
        System.out.println("topic = " + topic);
        System.out.println("qos = " + qos);
        System.out.println("payload = " + payload);
    }
}
