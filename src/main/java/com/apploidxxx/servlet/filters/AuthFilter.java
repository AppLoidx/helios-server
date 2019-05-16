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
 * Запрещает входить без авторизации к пути app/*
 *
 * @author Arthur Kupriyanov
 */
@WebFilter("/app/*")
public class AuthFilter implements Filter {
    @Inject
    UserBean userBean;

    @Override
    public void init(FilterConfig config) {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (session == null || userBean.getUser() == null) {
            response.sendRedirect(request.getContextPath() + "/"); // No logged-in user found, so redirect to login page.
        } else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {

    }

}