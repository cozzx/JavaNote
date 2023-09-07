package com.zt.start.robot.annotation;

import com.zt.start.robot.RobotAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自动配置注解
 * 使用方式二：使用此注解
 *
 * @author zt
 * @since 2023/8/31 16:24
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(RobotAutoConfiguration.class)
public @interface EnableRobot {
}
