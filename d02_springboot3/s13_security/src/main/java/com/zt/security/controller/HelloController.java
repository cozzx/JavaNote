package com.zt.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @since 2023/9/6 07:11
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!Spring Security";
    }


    @PreAuthorize("hasAuthority('world_exec')")
    @GetMapping("/world")
    public String world() {
        return "Hello World!!!";
    }
}
