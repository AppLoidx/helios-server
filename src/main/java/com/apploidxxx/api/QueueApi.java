package com.apploidxxx.api;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.entity.Session;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.QueueService;
import com.apploidxxx.entity.dao.SessionService;
import com.apploidxxx.entity.dao.UserService;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
@Path("/queue")
@Produces(MediaType.APPLICATION_JSON)
public class QueueApi {


    @GET
    public List<User> getUsers(@NotNull @QueryParam("queue_name") String queueName,
                              @QueryParam("type") String type){
        if (type == null) type = "members";

        QueueService qs = new QueueService();
        com.apploidxxx.entity.Queue q = qs.findQueue(queueName);

        if (q!=null){
            switch (type.toLowerCase()){
                case "members":
                    return q.getMembersList();
                case "super_users":
                    return new ArrayList<>(q.getSuperUsers());
                default:
                    return null;
            }

        } else {
            return null;
        }
    }

    @PUT
    public String joinQueue(@NotNull@QueryParam("queue_name") String queueName,
                            @NotNull@QueryParam("session") String sessionId){
        if (sessionId == null){
            return "{status: 401, message: 'Вы не авторизованы'}";
        }

        SessionService ss = new SessionService();
        Session userSession = ss.findSession(sessionId);
        User user;
        if (userSession==null){
            return "Ошибка при обработке session";
        } else {
            user = userSession.getUser();
        }

        QueueService qs = new QueueService();
        com.apploidxxx.entity.Queue q = qs.findQueue(queueName);
        if (q==null){
            return "{status: 400, message: Couldn't found queue}";
        }
        if (q.getMembers().contains(user)){
            return "{status: 400, message: You are already in queue}";
        } else {
            q.addUser(user);
            qs.updateQueue(q);
            new UserService().updateUser(user);

            return "{status: 200}";
        }

    }

    @POST
    public String createQueue(@NotNull @QueryParam("queue_name") String queueName,
                              @QueryParam("session") String sessionId){

        if (sessionId == null){
            return "{status: 401, message: 'Вы не авторизованы'}";
        }

        SessionService ss = new SessionService();
        Session userSession = ss.findSession(sessionId);
        User user;
        if (userSession==null){
            return "Ошибка при обработке session";
        } else {
            user = userSession.getUser();
        }
        QueueService qs = new QueueService();
        com.apploidxxx.entity.Queue q = new com.apploidxxx.entity.Queue(queueName);
        q.addSuperUser(user);
        try {
            qs.saveQueue(q);
            return "{status: 200}";
        }catch (Exception e){
            return "{status: 400, message: " + e.getMessage() +"}";
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteQueue(@NotNull@QueryParam("queue_name") String queueName,
                              @QueryParam("user_name") String userName,
                              @QueryParam("session") String sessionId){


        if (sessionId == null){
            return "{status: 401, message: 'Вы не авторизованы'}";
        }

        SessionService ss = new SessionService();
        Session userSession = ss.findSession(sessionId);
        User user;
        if (userSession==null){
            return "Ошибка при обработке session";
        } else {
            user = userSession.getUser();
        }

        if (userName!=null){
            return deleteUser(userName, queueName, user);
        } else {
            return deleteQueue(queueName, user);
        }
    }

    private String deleteUser(String username, String queueName, User user){
        if (user ==null){
            return "{status: 401, message: 'Вы не авторизованы'}";
        }

        QueueService qs = new QueueService();
        com.apploidxxx.entity.Queue q = qs.findQueue(queueName);
        if (q.getSuperUsers().contains(user)){
            User delUser = new UserService().findByName(username);
            if (delUser == null){
                return "{status: 400, message: 'Пользователь не найден'}";
            }
            q.getMembers().remove(delUser);
            return "{status: 200}";
        } else {
            return "{status: 403, message: 'У вас недостаточно прав'}";
        }

    }
    private String deleteQueue(String queueName, User user){
        QueueService qs = new QueueService();
        com.apploidxxx.entity.Queue q = qs.findQueue(queueName);

        if (q!=null){
            if (q.getSuperUsers().contains(user)){
                qs.deleteQueue(q);
                return "{status: 200}";
            } else {
                return "{status: 403, message: 'У вас недостаточно прав'}";
            }
        } else {
            return "{status: 400, message: 'Очередь с таким именем не найдена'}";
        }
    }
}
