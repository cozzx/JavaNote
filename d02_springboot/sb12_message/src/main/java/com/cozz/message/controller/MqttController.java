package com.cozz.message.controller;

import com.cozz.message.service.MqttGateWayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2024/3/15
 **/
@RestController
@RequestMapping("mqtt")
@Tag(name = "mqtt 测试")
public class MqttController {

    @Autowired
    MqttGateWayService mqttGateWayService;

    @PostMapping(value = "/send")
    @Operation(summary = "发送消息", description = "向指定主题中发送消息")
    public String sendMqtt(@RequestParam(value = "topic", required = true) @Parameter(description = "MQTT的主题") String topic) {
        mqttGateWayService.sendMessageToMqtt("Hello", topic);
        return "topic: " + topic + "; msg: Hello";
    }
}
