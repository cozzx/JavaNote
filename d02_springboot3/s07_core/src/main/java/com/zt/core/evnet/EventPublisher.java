package com.zt.core.evnet;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @since 2023/8/31 21:11
 **/
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

    /**
     * 底层发送事件用的组件，Springboot 会通过 ApplicationEventPublisherAware 接口自动注入
     * 事件是广播出去的，所有监听这个时间的监听器都可以收到
     */
    ApplicationEventPublisher applicationEventPublisher;

    /**
     * 会被自动调用，把真正发事件的底层组组件给我们注入进来
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 发送事件
     */
    public void sendEvent(ApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
