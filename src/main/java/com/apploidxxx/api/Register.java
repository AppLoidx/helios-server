package com.apploidxxx.api;

import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.UserService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * @author Arthur Kupriyanov
 */
@Path("/register")
public class Register {
    @POST
    public Response register(@NotNull @QueryParam("username") String username,
                             @NotNull @QueryParam("password") String password,
                             @NotNull @QueryParam("first_name") String firstName,
                             @NotNull @QueryParam("last_name") String lastName){
        UserService us = new UserService();
        if (us.findByName(username)==null){
            System.out.println(firstName);
            us.saveUser(new User(username, password, firstName, lastName));
            return Response.ok().build();
        } else {
            return Response.status(400).build();
        }
    }
}
