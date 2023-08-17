package test.thymeleaf.fruit.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.thymeleaf.fruit.dao.FruitDAO;
import test.thymeleaf.fruit.dao.impl.FruitDAOImpl;
import test.thymeleaf.fruit.pojo.Fruit;
import test.thymeleaf.ssm.dispatch.ViewBaseServlet;

import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        response.sendRedirect("index");
    }
}