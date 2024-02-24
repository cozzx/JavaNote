package test.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * servlet session 测试
 *
 * @author zt
 * @date 2023/8/5
 **/
public class SessionTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // 获取session,如果获取不到，则创建一个新的
        HttpSession session = req.getSession();
        System.out.println("session ID : " + session.getId());

        // 获取参数
        String username = req.getParameter("username");

        // 赋值与取值
        session.setAttribute("username", username);
        Object name = session.getAttribute("username");
        System.out.println(name);
    }
}
