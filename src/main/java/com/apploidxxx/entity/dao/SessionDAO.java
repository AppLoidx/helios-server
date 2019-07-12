package com.apploidxxx.entity.dao;

import com.apploidxxx.ds.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;

/**
 * @author Arthur Kupriyanov
 */
class SessionDAO {
    private DAOBasicOperations<com.apploidxxx.entity.Session> basicOperations = new DAOBasicOperations<>();

    com.apploidxxx.entity.Session findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(com.apploidxxx.entity.Session.class, id);
    }

    com.apploidxxx.entity.Session findSessionId(String sessionId) {
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()
        ){
            return session.createQuery("from Session where sessionId='" + sessionId + "'", com.apploidxxx.entity.Session.class).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    void save(com.apploidxxx.entity.Session s) {
        basicOperations.save(s);
    }

    void update(com.apploidxxx.entity.Session s) {
        basicOperations.update(s);
    }

    void delete(com.apploidxxx.entity.Session s) {
        basicOperations.delete(s);
    }

}
