package com.apploidxxx.servlet.filters;

import com.apploidxxx.beans.UserBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Запрещает авторизацию, если пользователь уже вошел
 *
 * @author Arthur Kupriyanov
 */
@WebFilter("/index.html")
public class LoginFilter implements Filter {
    @Inject
    UserBean userBean;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if (session != null && userBean.getUser() != null) {
            response.sendRedirect(request.getContextPath() + "/app/index.xhtml");    // logged
        } else {
            filterChain.doFilter(servletRequest, servletResponse);         // non-logged
        }
    }
    @Override
    public void destroy() {

    }
}
