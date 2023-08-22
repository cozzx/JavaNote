package com.zt.spring6.autowired;

import org.springframework.beans.factory.annotation.Autowired;
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
     * 方式一：属性注入
     * Autowired 注解默认根据类型装配 byType，如果想根据名称装配，需要配合 @Qualifier 注解一起用
     */
    @Autowired
    private UserService userService;


    /**
     * 方式二：set注入
     */
//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }


    /**
     * 方式三：构造方法注入
     */
//    private UserService userService;
//
//    // 当有参数的构造方法只有一个时，@Autowired 注解可以省略。
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }


    /**
     * 方式四：形参注入
     */
//    private UserService userService;
//
//    public UserController(@Autowired UserService userService) {
//        this.userService = userService;
//    }
    public void add() {
        System.out.println("controller........");
        userService.add();
    }
}
