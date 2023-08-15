package test.thymeleaf.fruit.reflectservlets;

import jakarta.servlet.ServletException;
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

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        String operate = request.getParameter("operate");
        if (operate == null || "".equals(operate)) {
            operate = "index";
        }

        switch (operate) {
            case "index":
                index(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "save":
                save(request, response);
                break;
            case "del":
                del(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "update":
                update(request, response);
                break;
            default:
                throw new RuntimeException("operate值非法!");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        response.sendRedirect("fruit.do");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fidStr = request.getParameter("fid");
        if (fidStr != null && !"".equals(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            super.processTemplate("reflect/edit", request, response);
        }
    }

    private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fidStr = request.getParameter("fid");
        if (fidStr != null && !"".equals(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);
            response.sendRedirect("fruit.do");
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.processTemplate("reflect/add", request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fname = request.getParameter("fname");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Integer fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitDAO.addFruit(fruit);
        response.sendRedirect("fruit.do");

    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Integer pageNo = 1;

        String oper = request.getParameter("oper");
        // 如果oper!=null 说明 通过表单的查询按钮点击过来的
        // 如果oper是空的，说明 不是通过表单的查询按钮点击过来的

        String keyword;
        if ("search".equals(oper)) {
            // 说明是点击表单查询发送过来的请求
            // 此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            // 说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            // 此时keyword应该从session作用域获取
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

        // 总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        // 总页数
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);
        // 视图名称 {view-prefix}index{view-suffix}
        super.processTemplate("reflect/index", request, response);
    }
}
