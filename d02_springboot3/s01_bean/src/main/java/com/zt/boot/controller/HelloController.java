package com.zt.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @since 2023/8/22 22:19
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello springboot3";
    }
}
