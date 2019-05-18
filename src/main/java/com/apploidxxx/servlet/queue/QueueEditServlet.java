package com.apploidxxx.servlet.queue;

import com.apploidxxx.beans.queue.QueueShowBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Arthur Kupriyanov
 */
@WebServlet("/v1/queueEdit")
public class QueueEditServlet extends HttpServlet {
    @Inject
    QueueShowBean queueShowBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=  req.getParameter("method");

        switch (method.toUpperCase()){
            case "LEAVE":
                queueShowBean.leaveQueue();
                resp.sendRedirect(req.getContextPath() + "/app/queue/manage.jsp");
                break;

            case "SHUFFLE":
                queueShowBean.shuffle();
                resp.sendRedirect(req.getContextPath() + "/v1/queue?queue_name="+ URLEncoder.encode(queueShowBean.getQueue().getName(), "utf-8"));
                break;
        }
        resp.setStatus(400);
    }
}
