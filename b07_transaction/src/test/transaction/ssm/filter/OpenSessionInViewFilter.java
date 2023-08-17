package test.transaction.ssm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import test.transaction.ssm.trans.TransactionManager;

import java.sql.SQLException;

/**
 * 在过滤器中设置事务的开启提交和回退
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            TransactionManager.beginTrans();
            System.out.println("开启事物...");
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();
            System.out.println("提交事物...");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                TransactionManager.rollback();
                System.out.println("回滚事物...");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
