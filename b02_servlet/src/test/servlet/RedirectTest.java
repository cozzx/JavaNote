package test.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * servlet 客户端重定向
 *
 * @author zt
 * @date 2023/8/5
 **/
public class RedirectTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.html");
    }
}
