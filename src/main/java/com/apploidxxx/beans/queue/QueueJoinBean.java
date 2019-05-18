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
import java.util.stream.Collectors;

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

    public String join(){

        Queue queue = service.findQueue(queueName);

        if (queue==null){
            showMessage("Такой очереди не существует", "500");
            return null;
        }

        List<User> members = queue.getMembersList();

            if (members.contains(userBean.getUser())){
                showMessage("Вы уже в очереди", "");
                return null;
            }

        queue.addUser(userBean.getUser());
        service.updateQueue(queue);
        showMessage("Вы успешно добавлены в очередь " + queue.getName(), "");
        return "/app/queue.xhtml";
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
    public String getRef(String queueName){
        return "/v1/queueJoin?queue_name="+queueName;
    }
    public List<Queue> getQueuesList(){
        QueueService q = new QueueService();
        List<Queue> queues = q.findAllQueues();
        return queues.stream().filter(qu-> !userBean.getUser().getQueueMember().contains(qu)).collect(Collectors.toList());
    }
}
