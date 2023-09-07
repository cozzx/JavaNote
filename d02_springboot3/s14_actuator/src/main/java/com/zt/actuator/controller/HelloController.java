package com.zt.actuator.controller;

import com.zt.actuator.component.MyHahaComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @since 2023/9/6 21:56
 **/
@RestController
public class HelloController {

    @Autowired
    MyHahaComponent myHahaComponent;

    @GetMapping("/hello")
    public String hello() {
        //业务调用
        myHahaComponent.hello();
        return "哈哈哈";
    }
}
