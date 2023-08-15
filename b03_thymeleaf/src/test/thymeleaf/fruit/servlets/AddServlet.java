package test.thymeleaf.fruit.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.thymeleaf.ssm.myspringmvc.ViewBaseServlet;

import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends ViewBaseServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.processTemplate("fruit/add", request, response);
    }
}
