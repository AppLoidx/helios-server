package com.apploidxxx.servlet.queue;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.beans.queue.QueuePasswordEnter;
import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.dao.QueueService;
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
@SuppressWarnings("ALL")
@WebServlet("/v1/queueJoin")
public class QueueJoinServlet extends HttpServlet {
    @Inject
    UserBean userBean;

    @Inject
    QueuePasswordEnter queuePasswordEnter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queueName = req.getParameter("queue_name");
        String password = req.getParameter("password");

        if (queueName==null){
            if (queuePasswordEnter.getQueueName()!=null){
                queueName = queuePasswordEnter.getQueueName();

            } else {
                resp.setStatus(400);
                return;
            }
        }

        QueueService service = new QueueService();
        Queue queue = service.findQueue(queueName);
        if (queue==null){
            resp.setStatus(404);
            return;
        }

        if (queue.getPassword()!=null){
            if (password==null){
                if (queuePasswordEnter.getPassword()!=null){
                    password = queuePasswordEnter.getPassword();
                    queuePasswordEnter.setPassword(null);
                }else {
                    queuePasswordEnter.setQueueName(queueName);
                    req.getRequestDispatcher("passwordEnter.xhtml").forward(req, resp);
                    return;
                }
            }
            if (!queue.getPassword().equals(password)){
                req.setAttribute("message", "Неверный пароль для очереди");
                queuePasswordEnter.setPassword(null);
                req.getRequestDispatcher("/info.jsp").forward(req, resp);

                return;
            }
        }

        queue.addUser(userBean.getUser());

        service.updateQueue(queue);
        userBean.updateUser();
        queuePasswordEnter.setPassword(null);
        resp.sendRedirect(req.getContextPath() + "/app/queue.xhtml");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queueName = req.getParameter("queue_name");
        String password = req.getParameter("password");

        if (queueName==null){
            if (queuePasswordEnter.getQueueName()!=null){
                queueName = queuePasswordEnter.getQueueName();

            } else {
                resp.setStatus(400);
                return;
            }
        }

        QueueService service = new QueueService();
        Queue queue = service.findQueue(queueName);
        if (queue==null){
            resp.setStatus(404);
            return;
        }

        if (queue.getPassword()!=null){
            if (password==null){
                if (queuePasswordEnter.getPassword()!=null){
                    password = queuePasswordEnter.getPassword();
                    queuePasswordEnter.setPassword(null);
                }else {
                    queuePasswordEnter.setQueueName(queueName);
                    queuePasswordEnter.setPassword(null);
                    req.getRequestDispatcher("passwordEnter.xhtml").forward(req, resp);

                    return;
                }
            }
            if (!queue.getPassword().equals(password)){
                req.setAttribute("message", "Неверный пароль для очереди");
                queuePasswordEnter.setPassword(null);
                req.getRequestDispatcher("/info.jsp").forward(req, resp);
                return;
            }
        }

        queue.addUser(userBean.getUser());
        service.updateQueue(queue);
        userBean.updateUser();
        queuePasswordEnter.setPassword(null);
        resp.sendRedirect(req.getContextPath() + "/app/queue.xhtml");
    }

}
