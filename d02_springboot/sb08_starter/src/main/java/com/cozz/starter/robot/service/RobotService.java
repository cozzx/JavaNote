package com.cozz.starter.robot.service;

import com.cozz.starter.robot.properties.RobotProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2023/8/31
 **/
@Service
public class RobotService {

    @Autowired
    RobotProperties robotProperties;

    public String sayHello() {
        return "你好：名字：【" + robotProperties.getName() + "】;年龄：【" + robotProperties.getAge() + "】";
    }
}
