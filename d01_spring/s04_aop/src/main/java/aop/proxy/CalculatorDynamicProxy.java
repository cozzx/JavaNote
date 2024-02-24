package aop.proxy;

import aop.example.Calculator;
import aop.example.CalculatorImpl;
import org.junit.jupiter.api.Test;

/**
 * 动态代理实现日志功能
 *
 * @author zt
 * @date 2023/8/21
 **/
public class CalculatorDynamicProxy {
    @Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.div(1, 1);
    }
}
