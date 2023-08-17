package test.filter.chain;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/demo02.do")
public class ChainFilter02 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ChainFilter02 11111");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("ChainFilter02 22222");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
