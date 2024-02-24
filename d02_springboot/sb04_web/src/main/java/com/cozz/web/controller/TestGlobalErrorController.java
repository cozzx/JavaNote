package com.cozz.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试全局异常处理
 *
 * @author zt
 * @date 2023/8/29
 **/
@RestController
public class TestGlobalErrorController {

    @GetMapping("testGloErr")
    public void testGloErr() {
        System.out.println(10 / 0);
    }
}
