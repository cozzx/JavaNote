package structural.proxy.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import structural.proxy.TrainStation2;

import java.lang.reflect.Method;

/**
 * 结构型设计模式 - 代理模式 - CGlib动态代理测试
 * 代售网点卖票
 *
 * @author zt
 * @date 2024/5/15
 **/
public class CGlibProxyFactory {

    // 返回对象是 TrainStation2 类的子类
    public TrainStation2 getProxyObject() {
        // 1、创建 Enhancer 对象，类似 JDK 动态代理的 Proxy 类
        Enhancer enhancer = new Enhancer();
        // 2、设置父类的字节码对象
        enhancer.setSuperclass(TrainStation2.class);
        // 3、设置回调方法
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * @param obj         生成的代理对象
             * @param method      被代理类的原始方法
             * @param args        调用方法的参数
             * @param methodProxy 代理类方法的对象，包含被代理类的原始方法
             */
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                // 代理方法增强
                System.out.println("代售网点卖票，收取手续费【cglib动态代理模式】");
                // 执行真实对象
                return methodProxy.invokeSuper(obj, args);
            }
        });
        // 4、创建代理对象
        return (TrainStation2) enhancer.create();
    }
}
