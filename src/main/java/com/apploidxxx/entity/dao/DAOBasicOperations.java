package com.apploidxxx.entity.dao;

import com.apploidxxx.ds.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Arthur Kupriyanov
 */
class DAOBasicOperations<T> {
    void save(T obj) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    void update(T obj) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(obj);
        tx1.commit();
        session.close();
    }

    void delete(T obj) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }
}
