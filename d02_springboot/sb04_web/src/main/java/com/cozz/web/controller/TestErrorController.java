package com.cozz.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试异常处理
 *
 * @author zt
 * @date 2023/8/29
 **/
@RestController
public class TestErrorController {

    /**
     * 注解 @ExceptionHandler 标识一个错误处理，默认只能处理这个类发生的制定错误
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "Ohho~~~, 原因" + e.getMessage();
    }

    @GetMapping("testErr")
    public void testErr() {
        System.out.println(10 / 0);
    }
}
