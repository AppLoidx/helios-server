package com.apploidxxx.servlet;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arthur Kupriyanov
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/auth")
public class LoginServlet extends HttpServlet {
    @Inject
    UserBean userBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username!=null && password!=null){
            UserService service = new UserService();
            User user = service.findByName(username);
            if (user!=null && user.getPassword().equals(password)) {
                userBean.setUsername(username);
                userBean.setUser(user);
                request.getRequestDispatcher("app/index.xhtml").forward(request, response);
            } else {
                request.setAttribute("message", "Неверный логин или пароль");
                request.getRequestDispatcher("info.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
