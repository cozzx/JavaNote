package structural.proxy.dynamicproxy;

import org.junit.Test;

/**
 * 结构型设计模式 - 代理模式 - JDK动态代理测试
 *
 * @author zt
 * @date 2024/5/15
 **/
public class JdkDynamicProxyTest {
    @Test
    public void test() {
        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        proxyFactory.getProxyObject().sell();
    }
}
