package com.apploidxxx.entity;

import com.apploidxxx.entity.dao.MessageService;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
@Entity
public class Chat {
    @Id
    @GeneratedValue
    private long id;

    public Chat(){}
    public Chat(Queue queue){
        this.queue = queue;
    }

    @OneToOne
    private Queue queue;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Message> messages;

    public synchronized void newMessage(User user, String message){
        if (queue.getMembers().contains(user) || queue.getSuperUsers().contains(user)){
            Message msg = new Message(user, message, this);
            MessageService ms= new MessageService();
            ms.saveMessage(msg);

            if (message==null){ messages = new LinkedHashSet<>();}

            messages.add(msg);

        }
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
