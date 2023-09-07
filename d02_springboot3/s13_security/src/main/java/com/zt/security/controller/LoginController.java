package com.zt.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zt
 * @since 2023/9/6 07:12
 **/
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
