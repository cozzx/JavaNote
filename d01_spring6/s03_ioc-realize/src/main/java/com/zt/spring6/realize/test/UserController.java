package com.zt.spring6.realize.test;

import com.zt.spring6.realize.core.anno.MyBean;
import com.zt.spring6.realize.core.anno.MyDI;

/**
 * @author zt
 * @since 2023/8/21 21:30
 **/
@MyBean
public class UserController {

    @MyDI
    private UserService userService;

    public void add() {
        System.out.println("controller........");
        userService.add();
    }
}
