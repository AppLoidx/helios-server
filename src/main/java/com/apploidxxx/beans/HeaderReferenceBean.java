package com.apploidxxx.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Arthur Kupriyanov
 */
@Named("headerBean")
@RequestScoped
public class HeaderReferenceBean {
    @Inject
    UserBean userBean;

    public String heliosAction(){
        return "/app/index.xhtml";
    }

    public String exitAction(){
        return userBean.logout();
    }

    public String userClick(){
        return null;
    }

    public String fullName(){
        if (userBean.getUser()==null) return "Not Authorized";
        return userBean.getUser().getFirstName() + " " + userBean.getUser().getLastName();
    }
}
