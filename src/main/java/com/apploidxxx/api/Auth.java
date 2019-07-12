package com.apploidxxx.api;

import com.apploidxxx.core.auth.PasswordChecker;
import com.apploidxxx.entity.Session;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.SessionService;
import com.apploidxxx.entity.dao.UserService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Arthur Kupriyanov
 */
@Path("/auth")
public class Auth {
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response authorize(  @NotNull @QueryParam("username") String username,
                                @NotNull @QueryParam("password") String password,
                                @NotNull@QueryParam("redirectUri") String redirectURI){
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
                try {
                    return Response.temporaryRedirect(new URI(redirectURI==null?"http://localhost:8080":redirectURI)).cookie(new NewCookie("session", s.getSessionId())).build();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }
                //return Response.ok().cookie(new NewCookie("session", s.getSessionId())).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
    }
}
