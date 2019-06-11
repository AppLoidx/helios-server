package com.apploidxxx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Arthur Kupriyanov
 */
@Entity
public class Message {

    public Message(){}
    public Message(User user, String message, Chat chat){
        this.user = user;
        this.message = message;
        this.chat = chat;
    }

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private Chat chat;

    @Column
    private String message;

    @ManyToOne
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
