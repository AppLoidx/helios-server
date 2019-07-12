package com.apploidxxx.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * Регистрация пользователя с подтвержденным письмом на email
 *
 * @author Arthur Kupriyanov
 */
@Path("/register/verify")
public class RegisterVerificationApi {

    /**
     *
     * @param id номер не проверенного пользователя
     * @param pin пин-код, высланный на почту
     * @return {@link Response}
     */
    @GET
    public Object verifyUser(@Valid @NotNull @QueryParam("id") long id,
                             @Valid @NotNull @QueryParam("pin") int pin){

        // TODO: write verify business method
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }



}
