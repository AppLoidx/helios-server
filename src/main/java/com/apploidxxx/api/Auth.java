package com.apploidxxx.api;

import com.apploidxxx.core.auth.PasswordChecker;
import com.apploidxxx.entity.Session;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.SessionService;
import com.apploidxxx.entity.dao.UserService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Arthur Kupriyanov
 */
@Path("/auth")
public class Auth {
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response authorize(  @NotNull @QueryParam("username") String username,
                                @NotNull @QueryParam("password") String password){
            UserService service = new UserService();
            User user = service.findByName(username);
            if (user!=null && PasswordChecker.checkEquals(password, user.getPassword())) {
                SessionService ss = new SessionService();

                Session s;
                if (user.getSession()!=null){
                    s = user.getSession();
                    s.generateSession(user);
                    ss.updateSession(s);
                } else {
                    s = new Session();
                    s.generateSession(user);
                    new UserService().updateUser(user);
                    ss.saveSession(s);
                }
                return Response.ok().entity(s.getSessionId()).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
    }
}
