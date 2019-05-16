package com.apploidxxx.entity;

import javax.persistence.*;

/**
 * @author Arthur Kupriyanov
 */
@Entity
public class ContactDetails {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @Column
    private String email;

    @Column
    private int vkontakteId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVkontakteId() {
        return vkontakteId;
    }

    public void setVkontakteId(int vkontakteId) {
        this.vkontakteId = vkontakteId;
    }
}
