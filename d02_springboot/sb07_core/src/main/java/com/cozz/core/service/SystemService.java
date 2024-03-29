package com.cozz.core.service;

import com.cozz.core.evnet.LoginSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2023/8/31
 **/
@Service
public class SystemService {

    public void recordLog(String username) {
        System.out.println(username + "登录，记录系统日志");
    }

    @Order(0)
    @EventListener
    public void onEvent(LoginSuccessEvent event) {
        System.out.println("=====  SystemService  监听到 LoginSuccessEvent 事件 =====");
        String username = (String) event.getSource();
        recordLog(username);
    }
}
