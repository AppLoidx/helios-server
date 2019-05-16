package com.apploidxxx.entity.dao;

import com.apploidxxx.ds.HibernateSessionFactoryUtil;
import com.apploidxxx.entity.Queue;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
class QueueDAO {
    Queue findById(String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                Queue queue = session.get(Queue.class, id);
                session.close();
                return queue;
    }

    private DAOBasicOperations<Queue> basicOperations = new DAOBasicOperations<>();

    void save(Queue queue) {
        System.out.println(queue);
        basicOperations.save(queue);
    }

    void update(Queue queue) {
        System.out.println(queue);
        basicOperations.update(queue);
    }

    void delete(Queue queue) {
        basicOperations.delete(queue);
    }

    List<Queue> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Queue> queueList = session.createQuery("From Queue", Queue.class).list();
        session.close();
        return queueList;
    }
}
