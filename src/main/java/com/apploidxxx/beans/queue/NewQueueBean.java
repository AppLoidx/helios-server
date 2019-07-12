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

    private boolean isQueuePrivate;

    private String name;
    private String description;
    private String password;

    private final String redirectUrl = "/app/queue.xhtml";

    public String createQueue(){
        if (!validateQueueName(name)) return null;
        QueueService service = new QueueService();
        Queue queue = getQueue(service);
        if (queue!=null) return null;
        if (!validateQueueName(name)) return null;

        queue = new Queue(name);
        defaultQueueCreation(queue);
        service.saveQueue(queue);
        return redirectUrl;
    }

    public String createQueueNAddUser(){
        if (!validateQueueName(name)) return null;
        QueueService service = new QueueService();
        Queue queue = getQueue(service);
        if (queue!=null) return null;
        if (!validateQueueName(name)) return null;
        queue = new Queue(name);
        defaultQueueCreation(queue);

        queue.addUser(userBean.getUser());

        service.saveQueue(queue);
        userBean.updateUser();
        return redirectUrl;
    }

    private void defaultQueueCreation(Queue q){
        q.addSuperUser(userBean.getUser());
        q.setDescription(description);
        q.setPassword(password);
    }
    private Queue getQueue(QueueService service){


        Queue queue = service.findQueue(name);

        if (queue!=null){
            showMessage("Очередь с таким именем уже существует");
            return queue;
        }

        return null;
    }

    private boolean validateQueueName(String name){
        if (name==null) return false;
        if (name.length()>18){
            showMessage("Имя очереди должно быть короче 18 символов");
            return false;
        }
        if (!name.matches("[a-zA-Z0-9а-яА-Я\\- ]+")){
            showMessage( "Имя очереди может состоять только из латинницы, пробела, чисел и символа дефиса");
            return false;
        }
        if (password!=null) {
            if (password.equals("") || password.matches("\\s*")) {
                password = null;
            }
        }
        return true;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void showMessage(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,""));

    }

    public boolean isQueuePrivate() {
        return isQueuePrivate;
    }

    public void setQueuePrivate(boolean aPrivate) {
        isQueuePrivate = aPrivate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
