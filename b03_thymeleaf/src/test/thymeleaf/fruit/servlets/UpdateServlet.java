package test.thymeleaf.fruit.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.thymeleaf.fruit.dao.FruitDAO;
import test.thymeleaf.fruit.dao.impl.FruitDAOImpl;
import test.thymeleaf.fruit.pojo.Fruit;
import test.thymeleaf.ssm.myspringmvc.ViewBaseServlet;

import java.io.IOException;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        //4.资源跳转
        //super.processTemplate("index",request,response);
        //request.getRequestDispatcher("index.html").forward(request,response);
        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取furitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        response.sendRedirect("index");
    }
}

// java.lang.NumberFormatException: For input string: ""