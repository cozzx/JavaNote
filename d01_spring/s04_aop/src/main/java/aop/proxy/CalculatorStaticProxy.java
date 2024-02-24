package aop.proxy;

import aop.example.Calculator;

/**
 * 静态代理实现日志功能
 * 通过代理类间接调用，使目标核心逻辑和日志逻辑解耦
 * 缺陷：1.不具备灵活性；2.没有统一管理
 *
 * @author zt
 * @date 2023/8/21
 **/
public class CalculatorStaticProxy implements Calculator {

    // 将被代理的目标对象声明为成员变量
    private Calculator target;

    public CalculatorStaticProxy(Calculator target) {
        this.target = target;
    }

    @Override
    public int add(int i, int j) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + i + "," + j);
        // 通过目标对象来实现核心业务逻辑
        int result = target.add(i, j);
        System.out.println("[日志] add 方法结束了，结果是：" + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("[日志] sub 方法开始了，参数是：" + i + "," + j);
        // 通过目标对象来实现核心业务逻辑
        int result = target.sub(i, j);
        System.out.println("[日志] sub 方法结束了，结果是：" + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("[日志] mul 方法开始了，参数是：" + i + "," + j);
        // 通过目标对象来实现核心业务逻辑
        int result = target.mul(i, j);
        System.out.println("[日志] mul 方法结束了，结果是：" + result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("[日志] div 方法开始了，参数是：" + i + "," + j);
        // 通过目标对象来实现核心业务逻辑
        int result = target.div(i, j);
        System.out.println("[日志] div 方法结束了，结果是：" + result);
        return result;
    }
}
