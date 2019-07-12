package com.apploidxxx.api;

import com.apploidxxx.api.util.UserInfo;
import com.apploidxxx.entity.Session;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.SessionService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Arthur Kupriyanov
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserApi {
    @GET
    public Object getInfo(@QueryParam("session") String sessionId,
                          @CookieParam("session") String cSession){

        if (sessionId == null){
            if (cSession == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                sessionId = cSession;
            }
        }

        SessionService ss = new SessionService();
        Session userSession = ss.findSession(sessionId);

        if (userSession==null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else {
            return new UserInfo( userSession.getUser());
        }
    }
}
