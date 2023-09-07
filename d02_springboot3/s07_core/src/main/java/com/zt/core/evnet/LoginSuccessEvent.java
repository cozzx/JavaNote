package com.zt.core.evnet;

import org.springframework.context.ApplicationEvent;

/**
 * 登录成功事件。所有事件都推荐继承 ApplicationEvent
 *
 * @author zt
 * @since 2023/8/31 21:01
 **/
public class LoginSuccessEvent extends ApplicationEvent {

    public LoginSuccessEvent(String username) {
        super(username);
    }
}
