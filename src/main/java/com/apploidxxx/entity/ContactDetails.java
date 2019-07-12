package com.apploidxxx.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * @author Arthur Kupriyanov
 */
@Entity
public class ContactDetails {

    public ContactDetails(){}
    public ContactDetails(User user, String email, long vkontakteId){
        this.user = user;
        this.email = email;
        this.vkontakteId = vkontakteId;
    }
    public ContactDetails(User user, String email){
        this.user = user;
        this.email = email;
    }

    @Id
    @GeneratedValue
    private Long id;

    @JsonbTransient
    @OneToOne
    private User user;

    @Column
    private String email;

    @Column
    private long vkontakteId;


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

    public long getVkontakteId() {
        return vkontakteId;
    }

    public void setVkontakteId(int vkontakteId) {
        this.vkontakteId = vkontakteId;
    }
}
