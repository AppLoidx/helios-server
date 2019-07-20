package com.apploidxxx.api.util;

import com.apploidxxx.entity.Queue;
import com.apploidxxx.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
public class UserInfo implements Serializable {
    private final User user;
    private final List<String[]> queues;

    public UserInfo(User user){
        this.user = user;
        queues = getQueues(user);
    }

    private List<String[]> getQueues(User user){
        Set<String[]> list = new HashSet<>();
        Set<Queue> set = new HashSet<>();
        set.addAll(user.getQueueMember());
        set.addAll(user.getQueueSuper());
        for (Queue q: set
             ) {
            list.add(new String[]{q.getName(), q.getFullname()});
        }
        return new ArrayList<>(list);
    }

    public User getUser() {
        return user;
    }

    public List<String[]> getQueues() {
        return queues;
    }
}
