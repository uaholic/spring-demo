package com.gyq.filter;

import javax.servlet.*;
import java.io.IOException;

public class WebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("do filter...");
    }

    @Override
    public void destroy() {
        System.out.println("filter destory...");
    }
}
