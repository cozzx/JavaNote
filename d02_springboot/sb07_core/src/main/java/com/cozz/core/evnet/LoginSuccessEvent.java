package com.cozz.core.evnet;

import org.springframework.context.ApplicationEvent;

/**
 * 登录成功事件。所有事件都推荐继承 ApplicationEvent
 *
 * @author zt
 * @date 2023/8/31
 **/
public class LoginSuccessEvent extends ApplicationEvent {

    public LoginSuccessEvent(String username) {
        super(username);
    }
}
