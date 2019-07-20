package com.apploidxxx.entity.dao;

import com.apploidxxx.ds.HibernateSessionFactoryUtil;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.VerificationContainer;

/**
 * @author Arthur Kupriyanov
 */
public class VerifyDAO {
    private DAOBasicOperations<VerificationContainer> daoBasicOperations = new DAOBasicOperations<>();

    public void save(VerificationContainer vc){
        daoBasicOperations.save(vc);
    }
    public void delete(VerificationContainer vc){
        daoBasicOperations.delete(vc);
    }
    public VerificationContainer get(User user){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(VerificationContainer.class, user.getId());
    }
}
