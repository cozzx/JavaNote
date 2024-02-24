package com.cozz.web.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author zt
 * @date 2023/8/29
 **/
@ControllerAdvice // 标识这个类集中处理所有 @Controller 发生的错误
public class GlobalExceptionHandler {

    @ResponseBody // 对象写出为json
    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception e) {
        return "Ohho！global exception" + e.getMessage();
    }
}
