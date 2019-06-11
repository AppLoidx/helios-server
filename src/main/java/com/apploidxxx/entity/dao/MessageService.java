package com.apploidxxx.entity.dao;

import com.apploidxxx.entity.Message;

/**
 * @author Arthur Kupriyanov
 */
public class MessageService {
    private MessageDAO dao = new MessageDAO();
    public void saveMessage(Message m){
        dao.save(m);
    }
}
