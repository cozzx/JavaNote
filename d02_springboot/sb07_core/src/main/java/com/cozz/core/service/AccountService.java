package com.cozz.core.service;

import com.cozz.core.evnet.LoginSuccessEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 监听事件
 * 方式一：实现 ApplicationListener 接口，传入具体事件类
 * 监听事件排序，使用 @Order 注解，默认按类名的字典排序
 *
 * @author zt
 * @date 2023/8/31
 **/
@Order(2)
@Service
public class AccountService implements ApplicationListener<LoginSuccessEvent> {

    public void addAccountScore(String username) {
        System.out.println(username + "签到加1积分");
    }

    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        System.out.println("=====  AccountService  监听到 LoginSuccessEvent 事件 =====");
        String username = (String) event.getSource();
        addAccountScore(username);
    }
}
