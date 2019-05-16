package com.apploidxxx.beans.queue;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Arthur Kupriyanov
 */
@Named("queueBean")
@RequestScoped
public class QueueBean{

    public String newQueue(){
        return "queue/new.xhtml";
    }
    public String selectQueue(){
        return "queue/join.xhtml";
    }
    public String manageQueue(){
        return null;
    }

}
