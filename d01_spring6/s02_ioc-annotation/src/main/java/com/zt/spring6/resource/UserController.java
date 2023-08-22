package com.zt.spring6.resource;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

/**
 * 注解 Controller 用于将控制层的类标识为 Spring 中的 Bean
 *
 * @author zt
 * @since 2023/8/21 19:30
 **/
@Controller
public class UserController {

    /**
     * 情况一：根据 name 注入
     */
//    @Resource(name = "myUserService")
//    private UserService userService;
//
//    public void add() {
//        System.out.println("controller........");
//        userService.add();
//    }


    /**
     * 情况二：没有指定 name 时，根据属性名进行查找
     */
    @Resource
    private UserService myUserService;

    public void add() {
        System.out.println("controller........");
        myUserService.add();
    }
}
