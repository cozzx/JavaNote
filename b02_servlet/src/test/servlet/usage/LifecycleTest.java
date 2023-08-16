package test.servlet.usage;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * servlet 生命周期
 *
 * @author zt
 * @since 2023/8/5 18:19
 **/
public class LifecycleTest extends HttpServlet {
    public LifecycleTest() {
        System.out.println("usage test HttpServlet() 正在实例化....");
    }

    @Override
    public void init() {
        System.out.println("usage test init() 正在初始化.....");

        // 读取 xml 中初始化的参数
        ServletConfig servletConfig = getServletConfig();
        String initValue = servletConfig.getInitParameter("hello");
        System.out.println("initValue = " + initValue);

        // 读取 xml 中上下文参数
        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = " + contextConfigLocation);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("usage test service() 正在服务.....");
    }

    @Override
    public void destroy() {
        System.out.println("usage test destroy() 正在销毁......");
    }
}
