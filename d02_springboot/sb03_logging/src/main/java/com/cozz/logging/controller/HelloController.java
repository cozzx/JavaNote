package com.cozz.logging.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2023/8/24
 **/
@RestController
@Slf4j
public class HelloController {

    /**
     * 记录日志方式一: 使用 @Slf4j 注解
     */
    @GetMapping("/hello/slf4j")
    public String helloSlf4j(String a, String b) {
        log.trace("trace 日志...");
        log.debug("debug 日志...");
        log.info("info 日志... 参数a:{} b:{}", a, b);
        log.warn("warn 日志...");
        log.error("error 日志...");
        return "helloSlf4j";
    }

    /**
     * 记录日志方式二: 使用 LoggerFactory
     */
    Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping("/hello/loggerFactory")
    public String helloLoggerFactory(String a, String b) {
        logger.trace("trace 日志...");
        logger.debug("debug 日志...");
        logger.info("info 日志... 参数a:{} b:{}", a, b);
        logger.warn("warn 日志...");
        logger.error("error 日志...");
        return "helloLoggerFactory";
    }

}
