package com.apploidxxx.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
@Entity
@Table(name = "queue")
public class Queue {

    public Queue(){}
    public Queue(String name){
        this.name = name;
    }

    @Id
    @Column(name = "queue_name", unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="QUEUE_MEMBERS",
            joinColumns = {@JoinColumn(name="queue_name")},
            inverseJoinColumns={@JoinColumn(name="users_id")})
    private Set<User> members;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="QUEUE_SUPER_USERS",
            joinColumns = {@JoinColumn(name="queue_name")},
            inverseJoinColumns={@JoinColumn(name="super_users")})

    private Set<User> superUsers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSuperUser(User u){
        if (superUsers==null) superUsers= new HashSet<>();
        superUsers.add(u);
    }

    public void deleteSuperUser(User u){
        if (members==null) return;
        superUsers.remove(u);
    }

    public void addUser(User u){
        if (members==null) members= new HashSet<>();
        members.add(u);
    }
    public void deleteUser(User u){
        if (members==null) return;
        members.remove(u);
    }


    public Set<User> getMembersList() {
        return members;
    }

    public void setMembersList(Set<User> membersList) {
        this.members = membersList;
    }

    public Set<User> getSuperUsers() {
        return superUsers;
    }

    public void setSuperUsers(Set<User> superUsers) {
        this.superUsers = superUsers;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "name='" + name + '\'' +
                ", members=" + members +
                ", superUsers=" + superUsers +
                '}';
    }
}
