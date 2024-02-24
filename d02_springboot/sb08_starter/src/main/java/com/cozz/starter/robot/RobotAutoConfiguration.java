package com.cozz.starter.robot;

import com.cozz.starter.robot.controller.RobotController;
import com.cozz.starter.robot.properties.RobotProperties;
import com.cozz.starter.robot.service.RobotService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置类
 * 使用方式一：引用此配置类
 *
 * @author zt
 * @date 2023/8/31
 **/
@Configuration
@Import({RobotController.class, RobotProperties.class, RobotService.class}) //给容器中导入Robot功能要用的所有组件
public class RobotAutoConfiguration {
}
