package com.apploidxxx.entity.dao;

import com.apploidxxx.ds.HibernateSessionFactoryUtil;
import com.apploidxxx.entity.User;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
class UsersDAO {
    private DAOBasicOperations<User> basicOperations = new DAOBasicOperations<>();
    User findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    User findByName(String username) {
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()
        ){
            return session.createQuery("from User where username='" + username + "'", User.class).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    void save(User user) {
        basicOperations.save(user);
    }

    void update(User user) {
        basicOperations.update(user);
    }

    void delete(User user) {
        basicOperations.delete(user);
    }
    List<User> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("From User", User.class).list();
        }
    }
}
