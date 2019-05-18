package com.apploidxxx.servlet.queue;

import com.apploidxxx.beans.queue.QueueShowBean;
import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.dao.QueueService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Arthur Kupriyanov
 */
@WebServlet("/v1/queue")
public class QueueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queueName = req.getParameter("queue_name");
        if (queueName==null){
            resp.setStatus(400);
            resp.getWriter().write("Не указан параметр queue_name");
            return;
        }
        System.out.println(queueName);
        System.out.println(URLDecoder.decode(queueName, "utf-8"));
        QueueService qService = new QueueService();
        Queue queue = qService.findQueue(queueName);

        if (queue==null){
            resp.setStatus(400);
            resp.getWriter().write("Очередь с таким именем не найдена");
            return;
        }

        req.getRequestDispatcher("/app/queue/queue.jsp").forward(req, resp);
    }
}
