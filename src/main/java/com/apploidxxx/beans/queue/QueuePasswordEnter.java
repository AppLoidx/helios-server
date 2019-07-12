package com.apploidxxx.beans.queue;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Arthur Kupriyanov
 */
@Named
@SessionScoped
public class QueuePasswordEnter implements Serializable {
    private String password;
    private String queueName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRef(){
            return "redirect.jsp";//"/queueJoin?queue_name=" + URLEncoder.encode(queueName, "utf-8") + "&password=" + password;

    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}
