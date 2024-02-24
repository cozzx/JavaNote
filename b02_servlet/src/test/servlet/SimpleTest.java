package test.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * servlet 简单测试
 *
 * @author zt
 * @date 2023/8/5
 **/
public class SimpleTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
    }
}
