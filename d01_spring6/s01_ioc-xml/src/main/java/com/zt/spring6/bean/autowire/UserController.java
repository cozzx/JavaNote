package com.zt.spring6.bean.autowire;

/**
 * @author zt
 * @since 2023/8/20 13:37
 **/
public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void saveUser() {
        userService.saveUser();
    }
}
