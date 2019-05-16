package com.apploidxxx.beans.register;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Arthur Kupriyanov
 */
@Named("regBean")
@SessionScoped
public class RegBean implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Inject
    UserBean userBean;

    public String register(){
        UserService userService = new UserService();
        User user = userService.findByName(username);

        if (user!=null){
            showMessage("Пользователь с именем " + username + " уже существует", "Ошибка");
            return null;
        }

        User newUser = new User(username, password, firstName, lastName);
        userService.saveUser(newUser);

        userBean.setUser(newUser);
        return "app/index.xhtml";


    }

    private void showMessage(String msg, String title){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, title));

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
