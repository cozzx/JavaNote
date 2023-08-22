package com.zt.spring6.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * bean 的后置处理器
 * 在生命周期的初始化前后添加额外的操作，需要实现 BeanPostProcessor 接口，且配置到 IOC 容器中
 * bean 后置处理器是针对 IOC 容器中所有 bean 都会执行
 *
 * @author zt
 * @since 2023/8/20 13:21
 **/
public class MyBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("☆☆☆" + beanName + " = " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("★★★" + beanName + " = " + bean);
        return bean;
    }
}
