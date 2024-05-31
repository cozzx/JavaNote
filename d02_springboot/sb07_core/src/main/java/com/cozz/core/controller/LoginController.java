package com.cozz.core.controller;

import com.cozz.core.evnet.EventPublisher;
import com.cozz.core.evnet.LoginSuccessEvent;
import com.cozz.core.service.AccountService;
import com.cozz.core.service.CouponService;
import com.cozz.core.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2023/8/31
 **/
@Slf4j
@RestController
public class LoginController {


    @Autowired
    AccountService accountService;
    @Autowired
    CouponService couponService;
    @Autowired
    SystemService systemService;

    @Autowired
    EventPublisher eventPublisher;

    @GetMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 处理登录
        log.info("处理登录");

        // 登录成功的附加操作, (传统模式, 依次调用)
        // 账户服务 自动签到加积分
//        accountService.addAccountScore(username);
        // 优惠服务 随机发放优惠券
//        couponService.sendCoupon(username);
        // 系统服务 记录用户登录信息
//        systemService.recordLog(username);

        // 设计模式：对新增开放，对修改关闭
        // 使用事件模式
        // 1. 创建事件信息
        LoginSuccessEvent loginSuccessEvent = new LoginSuccessEvent(username);
        // 2. 发送事件
        eventPublisher.sendEvent(loginSuccessEvent);

        return username + "登录成功";
    }
}
