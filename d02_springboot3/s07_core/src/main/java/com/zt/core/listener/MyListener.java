package com.zt.core.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author zt
 * @since 2023/8/31 07:19
 **/
public class MyListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("####事件####到达####" + event);
    }
}
