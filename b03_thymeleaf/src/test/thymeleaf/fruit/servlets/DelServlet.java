package test.thymeleaf.fruit.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.thymeleaf.fruit.dao.FruitDAO;
import test.thymeleaf.fruit.dao.impl.FruitDAOImpl;
import test.thymeleaf.ssm.myspringmvc.ViewBaseServlet;

import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fidStr = request.getParameter("fid");
        if (fidStr != null && !"".equals(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);

            //super.processTemplate("index",request,response);
            response.sendRedirect("index");
        }
    }
}
