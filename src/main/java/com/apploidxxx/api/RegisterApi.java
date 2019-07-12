package com.apploidxxx.api;

import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * @author Arthur Kupriyanov
 */
@Path("/register")
public class RegisterApi {
    @POST
    public Response register(@Valid@NotNull @QueryParam("username") String username,
                             @Valid @NotNull @QueryParam("password") String password,
                             @Valid@NotNull @QueryParam("firstName") String firstName,
                             @Valid@NotNull @QueryParam("lastName") String lastName,
                             @Valid@NotNull@QueryParam("email") String email){
        UserService us = new UserService();
        if (us.findByName(username)==null){
            us.saveUser(new User(username, password, firstName, lastName, email));
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }
}
