package structural.proxy.dynamicproxy;

import structural.proxy.SellTicket;
import structural.proxy.TrainStation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 结构型设计模式 - 代理模式 - JDK动态代理测试
 * 代售网点卖票
 *
 * @author zt
 * @date 2024/5/15
 **/
public class JdkProxyFactory {
    private final TrainStation trainStation = new TrainStation();

    // 返回对象是 SellTicket 接口实现类
    public SellTicket getProxyObject() {
        // 使用 Proxy 获取代理对象
        // newProxyInstance 参数说明：
        // 1、ClassLoader loader : 类加载器，用户加载代理类，使用真实对象的类加载器即可
        // 2、Class<?>[] interfaces : 真实对象所实现的接口，代理模式真实对象和代理对象实现相同接口
        // 3、InvocationHandler h : 代理对象的调用处理程序
        return (SellTicket) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 代理方法增强
                        System.out.println("代售网点卖票，收取手续费【jdk动态代理模式】");
                        // 执行真实对象
                        return method.invoke(trainStation, args);
                    }
                });
    }
}
