package com.apploidxxx;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.ds.UserService;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.UsersDAO;

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
//            UserService service = new UserService();
//            service.saveUser(new User(username, password));
            userBean.setPassword(password);
            userBean.setUsername(username);
            request.getRequestDispatcher("page.xhtml").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
