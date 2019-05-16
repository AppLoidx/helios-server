package com.apploidxxx.beans.queue;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.QueueService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
@Named("queueJoinBean")
@RequestScoped
public class QueueJoinBean {
    @Inject
    UserBean userBean;
    private String queueName;

    QueueService service = new QueueService();

    public void join(){

        Queue queue = service.findQueue(queueName);

        if (queue==null){
            showMessage("Такой очереди не существует", "500");
            return;
        }

        Set<User> members = queue.getMembersList();
        for (User u : members){
            if (u.equals(userBean.getUser())){
                showMessage("Вы уже в очереди", "");
                return;
            }
        }
        queue.addUser(userBean.getUser());
        service.updateQueue(queue);
        showMessage("Вы успешно добавлены в очередь " + queue.getName(), "");
    }
    public String getQueueMenu(){
        StringBuilder sb = new StringBuilder();
        List<Queue> queueList = service.findAllQueues();
        sb.append("Список доступных очередей: ");
        for (Queue q: queueList){
            sb.append(q.getName()).append(",");
        }

        return sb.toString();
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    private void showMessage(String msg, String title){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, title));

    }
}
