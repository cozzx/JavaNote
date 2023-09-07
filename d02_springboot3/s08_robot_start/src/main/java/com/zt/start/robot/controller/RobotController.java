package com.zt.start.robot.controller;

import com.zt.start.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @since 2023/8/31 22:18
 **/
@RestController
public class RobotController {

    @Autowired
    RobotService robotService;

    @GetMapping("/robot/hello")
    public String sayHello() {
        return robotService.sayHello();
    }
}
