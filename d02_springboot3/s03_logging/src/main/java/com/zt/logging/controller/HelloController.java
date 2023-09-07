package com.zt.logging.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @since 2023/8/24 19:37
 **/
@Slf4j
@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello/logger")
    public String helloLogger(String a, String b) {

        logger.trace("trace 日志...");
        logger.debug("debug 日志...");
        logger.info("info 日志... 参数a:{} b:{}", a, b);
        logger.warn("warn 日志...");
        logger.error("error 日志...");
        return "helloLogger";
    }

    @GetMapping("/hello/slf4j")
    public String helloSlf4j(String a, String b) {
        logger.trace("trace 日志...");
        logger.debug("debug 日志...");
        logger.info("info 日志... 参数a:{} b:{}", a, b);
        logger.warn("warn 日志...");
        logger.error("error 日志...");
        return "helloSlf4j";
    }

    @GetMapping("/hello/archive")
    public String helloArchive(String a, String b) {
        for (int i = 0; i < 1000; i++) {
            logger.trace("trace 日志...");
            logger.debug("debug 日志...");
            logger.info("info 日志... 参数a:{} b:{}", a, b);
            logger.warn("warn 日志...");
            logger.error("error 日志...");
        }
        return "helloArchive";
    }
}
