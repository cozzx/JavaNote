package com.zt.spring6.aop.proxy;

import com.zt.spring6.aop.example.Calculator;
import com.zt.spring6.aop.example.CalculatorImpl;
import org.junit.jupiter.api.Test;

/**
 * 动态代理实现日志功能
 *
 * @author zt
 * @since 2023/8/21 15:44
 **/
public class CalculatorDynamicProxy {
    @Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.div(1, 1);
    }
}
