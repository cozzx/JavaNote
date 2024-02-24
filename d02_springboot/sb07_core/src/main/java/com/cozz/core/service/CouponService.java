package com.cozz.core.service;

import com.cozz.core.evnet.LoginSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 监听事件
 * 方式二：方法中标注 @EventListener 注解，传入具体事件类
 *
 * @author zt
 * @date 2023/8/31
 **/
@Service
public class CouponService {

    public void sendCoupon(String username) {
        System.out.println(username + "发优惠券");
    }

    /**
     * 监听事件，标注 @EventListener 注解，传入具体事件类
     * 监听事件排序，使用 @Order 注解，默认按类名的字典排序
     */
    @Order(1)
    @EventListener
    public void onEvent(LoginSuccessEvent event) {
        System.out.println("=====  CouponService  监听到 LoginSuccessEvent 事件 =====");
        String username = (String) event.getSource();
        sendCoupon(username);
    }
}
