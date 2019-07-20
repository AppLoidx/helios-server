package com.apploidxxx.api;

import com.apploidxxx.api.util.UserSession;
import com.apploidxxx.entity.Chat;
import com.apploidxxx.entity.Message;
import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.ChatService;
import com.apploidxxx.entity.dao.QueueService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
                                    @Valid@NotNull@QueryParam("lastMsgId") int lastMsgId){
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
    public Object addMessage(@PathParam("queueName") String queueName,
                             @Valid@NotNull @QueryParam("message") String message,
                             @Valid@NotNull@QueryParam("session") String session){

            User user = UserSession.getUser(session);
            if (user == null) return Response.status(Response.Status.BAD_REQUEST).build();

            QueueService qs = new QueueService();
            Queue q = qs.findQueue(queueName);
            if (q==null){
                return Response.status(Response.Status.NOT_FOUND).entity("Queue not found").build();
            } else {
                Chat chat = q.getChat();
                if (message.equals("")) return Response.status(Response.Status.BAD_REQUEST).entity("invalid message");
                chat.newMessage(user, message);
                new ChatService().updateChat(chat);
                return Response.ok().build();
            }
    }
}
