package com.apploidxxx.api;

import com.apploidxxx.api.util.UserSession;
import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.QueueService;
import com.apploidxxx.entity.dao.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Arthur Kupriyanov
 */
@Path("/queue")
@Produces(MediaType.APPLICATION_JSON)
public class QueueApi {


    @GET
    public Object getQueue(@NotNull@QueryParam("queueName") String queueName){

        QueueService qs = new QueueService();
        Queue q = qs.findQueue(queueName);
        if (q == null) return Response.status(Response.Status.BAD_REQUEST).build();
        else return q;
    }

    @PUT
    public Object joinQueue(@NotNull@QueryParam("queueName") String queueName,
                            @QueryParam("session") String session){

        User user = UserSession.getUser(session);
        if (user == null) return Response.status(Response.Status.BAD_REQUEST).build();

        QueueService qs = new QueueService();
        com.apploidxxx.entity.Queue q = qs.findQueue(queueName);
        if (q==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (q.getMembers().contains(user)){
            return Response.status(Response.Status.BAD_REQUEST).entity("You are already in queue").build();
        } else {
            q.addUser(user);
            qs.updateQueue(q);
            new UserService().updateUser(user);

            return Response.ok();
        }

    }

    @POST
    public Object createQueue(@Valid@NotNull@QueryParam("queueName") String queueName,
                              @Valid@NotNull@QueryParam("session") String session,
                              @QueryParam("fullname") String fullname){

        User user = UserSession.getUser(session);
        if (user == null) return Response.status(Response.Status.BAD_REQUEST).build();
        QueueService qs = new QueueService();

        com.apploidxxx.entity.Queue q = new com.apploidxxx.entity.Queue(queueName, fullname==null?queueName:fullname);
        q.addSuperUser(user);
        try {
            qs.saveQueue(q);
            return Response.ok().build();
        }catch (Exception e){
            return "{status: 400, message: " + e.getMessage() +"}";
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteQueue(@NotNull@QueryParam("queueName") String queueName,
                              @Valid@QueryParam("user_name") String userName,
                              @Valid@QueryParam("session") String session){

        User user = UserSession.getUser(session);
        if (user == null) return Response.status(Response.Status.BAD_REQUEST).build();

        if (userName!=null){
            return deleteUser(userName, queueName, user);
        } else {
            return deleteQueue(queueName, user);
        }
    }

    private Response deleteUser(String username, String queueName, User user){
        if (user ==null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        QueueService qs = new QueueService();
        com.apploidxxx.entity.Queue q = qs.findQueue(queueName);
        if (q.getSuperUsers().contains(user)){
            User delUser = new UserService().findByName(username);
            if (delUser == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            q.getMembers().remove(delUser);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }
    private Response deleteQueue(String queueName, User user){
        QueueService qs = new QueueService();
        com.apploidxxx.entity.Queue q = qs.findQueue(queueName);

        if (q!=null){
            if (q.getSuperUsers().contains(user)){
                qs.deleteQueue(q);
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
