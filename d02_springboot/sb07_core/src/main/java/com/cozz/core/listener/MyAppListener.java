package com.cozz.core.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * springboot 应用生命周期监听
 * Listener 需要在 META-INF/spring.factories 配置
 * 1. 引导: 利用 BootstrapContext 引导整个项目启动
 * starting
 * environmentPrepared
 * 2、启动
 * contextPrepared
 * contextLoaded
 * started
 * ready
 * 3、运行
 * 以前步骤都正确执行，代表容器running
 *
 * @author zt
 * @date 2023/8/31
 **/
public class MyAppListener implements SpringApplicationRunListener {

    /**
     * 应用开始, SpringApplication 的 run 方法一调用, 只要有了 BootstrapContext 就执行
     */
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        SpringApplicationRunListener.super.starting(bootstrapContext);
        System.out.println("=====starting=====正在启动======");
    }

    /**
     * 环境准备好（把启动参数等绑定到环境变量中），但是ioc还没有创建；【调一次】
     */
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        SpringApplicationRunListener.super.environmentPrepared(bootstrapContext, environment);
        System.out.println("=====environmentPrepared=====环境准备完成======");
    }

    /**
     * ioc容器创建并准备好，但是sources（主配置类）没加载。并关闭引导上下文；组件都没创建  【调一次】
     */
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.contextPrepared(context);
        System.out.println("=====contextPrepared=====ioc容器准备完成======");
    }

    /**
     * ioc容器加载。主配置类加载进去了。但是ioc容器还没刷新（我们的bean没创建）。
     */
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.contextLoaded(context);
        System.out.println("=====contextLoaded=====ioc容器加载完成======");
    }

    /**
     * ioc容器刷新了（所有bean造好了），但是 runner 没调用。
     */
    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        SpringApplicationRunListener.super.started(context, timeTaken);
        System.out.println("=====started=====启动完成======");
    }

    /**
     * ioc容器刷新了（所有bean造好了），所有runner调用完了。
     */
    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        SpringApplicationRunListener.super.ready(context, timeTaken);
        System.out.println("=====ready=====准备就绪======");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        SpringApplicationRunListener.super.failed(context, exception);
        System.out.println("=====failed=====应用启动失败======");
    }
}
