package com.apploidxxx.entity;

import javax.persistence.*;

/**
 * @author Arthur Kupriyanov
 */
@Table(name="users")
@Entity
public class User {

    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

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

}
