package structural.proxy.staticproxy;

import org.junit.Test;

/**
 * 结构型设计模式 - 代理模式 - 静态代理测试
 *
 * @author zt
 * @date 2024/5/15
 **/
public class StaticProxyTest {
    @Test
    public void test() {
        ProxyPoint proxyPoint = new ProxyPoint();
        proxyPoint.sell();
    }
}
