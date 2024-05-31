package structural.proxy.dynamicproxy;

import org.junit.Test;

/**
 * 结构型设计模式 - 代理模式 - cglib动态代理测试
 *
 * @author zt
 * @date 2024/5/15
 **/
public class CGlibDynamicProxyTest {

    /**
     * Java 9+ 对反射访问施加了更严格的限制，测试时 VM options 添加 --add-opens java.base/java.lang=ALL-UNNAMED
     */
    @Test
    public void test() {
        CGlibProxyFactory proxyFactory = new CGlibProxyFactory();
        proxyFactory.getProxyObject().sell();
    }
}
