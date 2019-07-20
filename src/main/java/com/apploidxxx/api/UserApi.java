package com.apploidxxx.api;

import com.apploidxxx.api.util.UserInfo;
import com.apploidxxx.entity.Session;
import com.apploidxxx.entity.dao.SessionService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Arthur Kupriyanov
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserApi {
    @GET
    public Object getInfo(@QueryParam("session") String sessionId){

        SessionService ss = new SessionService();
        Session userSession = ss.findSession(sessionId);

        if (userSession==null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else {
            return new UserInfo( userSession.getUser());
        }
    }
}
