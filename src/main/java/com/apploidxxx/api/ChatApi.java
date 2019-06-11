package com.apploidxxx.api;

import com.apploidxxx.entity.*;
import com.apploidxxx.entity.dao.ChatService;
import com.apploidxxx.entity.dao.QueueService;
import com.apploidxxx.entity.dao.SessionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
public class ChatApi {
    @GET
    @Path("/{queueName}")
    public Set<Message> getMessages(@PathParam("queueName") String queueName,
                                    @Valid@NotNull@QueryParam("last_msg_id") int lastMsgId){
        QueueService qs = new QueueService();
        Queue queue  = qs.findQueue(queueName);
        if (queue==null){
            return null;
        }

        Set<Message> messages = queue.getChat().getMessages();
        Set<Message> response = new LinkedHashSet<>();
        for (Message m : messages){
            if (m.getId() > lastMsgId){
                response.add(m);
            }
        }

        return response;
    }

    @PUT
    @Path("/{queueName}")
    public String addMessage(@PathParam("queueName") String queueName,
                             @NotNull @QueryParam("message") String message,
                             @NotNull @QueryParam("session") String session){
            if (session == null){
                return "{status: 401, message: 'Вы не авторизованы'}";
            }

            SessionService ss = new SessionService();
            Session userSession = ss.findSession(session);
            User user;
            if (userSession==null){
                return "Ошибка при обработке session";
            } else {
                user = userSession.getUser();
            }

            QueueService qs = new QueueService();
            Queue q = qs.findQueue(queueName);
            if (q==null){
                return "{status: 400, message: 'queue not found'}";
            } else {
                Chat chat = q.getChat();
                if (message.equals("")) return "{status: 400, message: 'bad parameter message'}";
                chat.newMessage(user, message);
                new ChatService().updateChat(chat);
                return "{status: 200}";
            }
    }
}
