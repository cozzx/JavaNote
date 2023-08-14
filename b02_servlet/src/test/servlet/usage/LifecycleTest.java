package test.servlet.usage;

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
