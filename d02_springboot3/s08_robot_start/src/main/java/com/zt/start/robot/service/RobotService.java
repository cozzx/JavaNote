package com.zt.start.robot.service;

import com.zt.start.robot.properties.RobotProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @since 2023/8/31 22:18
 **/
@Service
public class RobotService {

    @Autowired
    RobotProperties robotProperties;

    public String sayHello() {
        return "你好：名字：【" + robotProperties.getName() + "】;年龄：【" + robotProperties.getAge() + "】";
    }
}
