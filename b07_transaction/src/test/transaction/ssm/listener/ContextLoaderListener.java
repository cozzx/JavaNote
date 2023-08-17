package test.transaction.ssm.listener;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import test.transaction.ssm.ioc.BeanFactory;
import test.transaction.ssm.ioc.ClassPathXmlApplicationContext;

/**
 * 监听上下文启动，在上下文启动的时候去创建IOC容器,然后将其保存到application作用域
 * 后面中央控制器再从application作用域中去获取IOC容器
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);

        // 1. 获取ServletContext对象
        ServletContext servletContext = sce.getServletContext();
        // 2. 获取上下文的初始化参数
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        // 3. 创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext();
        // 4. 将IOC容器保存到application作用域
        servletContext.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
