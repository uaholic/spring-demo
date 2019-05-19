package com.gyq.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class WebFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(WebFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("filter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        logger.debug("do filter...");
    }

    @Override
    public void destroy() {
        logger.debug("filter destory...");
    }
}
