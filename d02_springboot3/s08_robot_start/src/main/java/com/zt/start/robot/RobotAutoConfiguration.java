package com.zt.start.robot;

import com.zt.start.robot.controller.RobotController;
import com.zt.start.robot.properties.RobotProperties;
import com.zt.start.robot.service.RobotService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置类
 * 使用方式一：引用此配置类
 *
 * @author zt
 * @since 2023/8/31 22:17
 **/
@Configuration
@Import({RobotController.class, RobotProperties.class, RobotService.class}) //给容器中导入Robot功能要用的所有组件
public class RobotAutoConfiguration {
}
