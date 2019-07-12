package com.apploidxxx.beans.queue;

import com.apploidxxx.beans.UserBean;
import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
@Named("queueManageBean")
@RequestScoped
public class QueueManageBean implements Serializable {
    @Inject
    UserBean userBean;

    public Set<Queue> getMemberQueues(){
        UserService service = new UserService();
        User user = service.findUser(userBean.getUser().getId());

        return user.getQueueMember();
    }
    public Set<Queue> getSuperQueues(){
        UserService service = new UserService();
        User user = service.findUser(userBean.getUser().getId());

        return user.getQueueSuper();
    }

    /**
     *
     * @return reference to servlet which handles queue name and redirects
     */
    public String getRef(String queueName, String failRedirectURL){
        try {
            return  "/v1/queue?queue_name="+ URLEncoder.encode(queueName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return failRedirectURL;
        }

    }


}
