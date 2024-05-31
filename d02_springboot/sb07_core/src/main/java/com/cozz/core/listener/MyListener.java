package com.cozz.core.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 想感知什么事件就把事件写入到泛型
 *
 * @author zt
 * @date 2023/8/31
 **/
public class MyListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("#####事件到达#####" + event);
    }
}
