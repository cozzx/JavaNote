package com.cozz.starter.robot.annotation;

import com.cozz.starter.robot.RobotAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自动配置注解
 * 使用方式二：使用此注解
 *
 * @author zt
 * @date 2023/8/31
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(RobotAutoConfiguration.class)
public @interface EnableRobot {
}
