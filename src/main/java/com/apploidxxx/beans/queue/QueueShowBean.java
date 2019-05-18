package com.apploidxxx.beans.queue;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.dao.QueueService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Arthur Kupriyanov
 */
@Named("queueShowBean")
@SessionScoped
public class QueueShowBean implements Serializable {
    @Inject
    private UserBean userBean;

    private Queue queue;

    public Queue getQueue(String queueName) {
        QueueService qService = new QueueService();
        queue = qService.findQueue(queueName);
        return queue;
    }
    public void init(String queueName){
        QueueService qService = new QueueService();
        queue = qService.findQueue(queueName);
    }
    public Queue getQueue(){
        return queue;
    }

    public String shuffle(){
        if (queue!=null){
            if (queue.getSuperUsers().contains(userBean.getUser())) {
                queue.shuffle();
            }
        }
        return "";
    }

    public boolean isSuperUser(){
        if (queue!=null){
            return queue.getSuperUsers().contains(userBean.getUser());
        } else {
            return false;
        }
    }

    public String leaveQueue(){
        if (queue!=null){
            queue.deleteUser(userBean.getUser());
            new QueueService().updateQueue(queue);
            userBean.updateUser();
        }

        return "";
    }
    public void swapRequest(){

    }
}
