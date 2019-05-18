package com.apploidxxx.beans;

import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Arthur Kupriyanov
 */
@Named
@SessionScoped
public class UserBean implements Serializable {
    private String username;
    private User user;

    public void updateUser(){
        user = new UserService().findUser(user.getId());
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.username = user.getUsername();
    }

    public String logout(){
        user = null;
        username = null;

        return "/index.jsp";
    }

    // TODO: add user update from ds
}
