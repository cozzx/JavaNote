package com.cozz.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2023/8/22
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello springboot";
    }
}
