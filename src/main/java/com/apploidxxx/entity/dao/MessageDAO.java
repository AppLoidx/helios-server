package com.apploidxxx.entity.dao;

import com.apploidxxx.ds.HibernateSessionFactoryUtil;
import com.apploidxxx.entity.Message;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class MessageDAO {
    private DAOBasicOperations<Message> basicOperations = new DAOBasicOperations<>();
    Message findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Message.class, id);
    }

    void save(Message user) {
        basicOperations.save(user);
    }

    void update(Message user) {
        basicOperations.update(user);
    }

    void delete(Message user) {
        basicOperations.delete(user);
    }
    List<Message> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("from Message", Message.class).list();
        }
    }
}
