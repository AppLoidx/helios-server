package com.apploidxxx.entity.dao;

import com.apploidxxx.ds.HibernateSessionFactoryUtil;
import com.apploidxxx.entity.Chat;
import com.apploidxxx.entity.Message;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class ChatDAO {
    private DAOBasicOperations<Chat> basicOperations = new DAOBasicOperations<>();
    Message findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Message.class, id);
    }

    void save(Chat chat) {
        basicOperations.save(chat);
    }

    void update(Chat chat) {
        basicOperations.update(chat);
    }

    void delete(Chat chat) {
        basicOperations.delete(chat);
    }
    List<Chat> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("from Chat", Chat.class).list();
        }
    }
}
