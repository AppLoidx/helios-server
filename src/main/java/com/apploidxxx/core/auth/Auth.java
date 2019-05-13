package com.apploidxxx.core.auth;

import com.apploidxxx.entity.User;

/**
 * @author Arthur Kupriyanov
 */
public class Auth {
    private boolean auth(String username, String password){
        if (username.equals("user") && password.equals("pass")){
            return true;
        }
        return false;
    }

    public User login(String username, String password){
        if (auth(username, password)){
            return new User(username, password); // TODO: from ds
        }

        return null;
    }
}
