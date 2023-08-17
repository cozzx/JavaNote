package test.thymeleaf.fruit.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.thymeleaf.fruit.dao.FruitDAO;
import test.thymeleaf.fruit.dao.impl.FruitDAOImpl;
import test.thymeleaf.ssm.dispatch.ViewBaseServlet;

import java.io.IOException;

@WebServlet("/del")
public class DelServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fidStr = request.getParameter("fid");
        if (fidStr != null && !"".equals(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);
            response.sendRedirect("index");
        }
    }
}
