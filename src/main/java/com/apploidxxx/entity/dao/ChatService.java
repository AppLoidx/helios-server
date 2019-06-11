package com.apploidxxx.entity.dao;

import com.apploidxxx.entity.Chat;

/**
 * @author Arthur Kupriyanov
 */
public class ChatService {
    ChatDAO chatDAO = new ChatDAO();
    public void saveChat(Chat c){
        chatDAO.save(c);
    }
    public void updateChat(Chat c){
        chatDAO.update(c);
    }
}
