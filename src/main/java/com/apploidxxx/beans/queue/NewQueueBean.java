package com.apploidxxx.beans.queue;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.dao.QueueService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Arthur Kupriyanov
 */
@Named("newQueueBean")
@RequestScoped
public class NewQueueBean implements Serializable {
    @Inject
    UserBean userBean;

    private String name;

    public String createQueue(){
        System.out.println(name);
        if (!name.matches("[a-zA-Z0-9\\-]+")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Имя очереди может состоять только из латинницы, чисел и символа дефиса",""));
            return null;
        }
        QueueService service = new QueueService();
        Queue queue = service.findQueue(name);

        if (queue!=null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Очередь с таким именем уже существует",""));
            return null;
        }

        queue = new Queue(name);
        queue.addSuperUser(userBean.getUser());
        System.out.println(queue);
        service.saveQueue(queue);
        return "/app/queue.xhtml";
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
