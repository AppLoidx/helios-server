package com.apploidxxx.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
@Table
@Entity
public class VerificationContainer {
    public VerificationContainer(){}

    @Id
    @OneToMany
    @Column(unique = true)
    Set<User> user;

    public boolean verificate(){
        return false;
        // TODO: write verification method
    }


}
