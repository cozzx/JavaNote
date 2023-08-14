package test.thymeleaf.fruit.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import test.thymeleaf.fruit.dao.FruitDAO;
import test.thymeleaf.fruit.dao.impl.FruitDAOImpl;
import test.thymeleaf.fruit.pojo.Fruit;
import test.thymeleaf.ssm.myspringmvc.ViewBaseServlet;

import java.io.IOException;
import java.util.List;

/**
 * Servlet从3.0版本开始支持注解方式的注册
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Integer pageNo = 1;

        String oper = request.getParameter("oper");
        //如果oper!=null 说明 通过表单的查询按钮点击过来的
        //如果oper是空的，说明 不是通过表单的查询按钮点击过来的

        String keyword = null;
        if ("search".equals(oper)) {
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if (pageNoStr != null && !"".equals(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);

        session.setAttribute("fruitList", fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);
        // 视图名称 {view-prefix}index{view-suffix}
        super.processTemplate("/index", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
