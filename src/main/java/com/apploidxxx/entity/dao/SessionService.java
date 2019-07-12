package com.apploidxxx.entity.dao;

import com.apploidxxx.entity.Session;

/**
 * @author Arthur Kupriyanov
 */
public class SessionService {
    private SessionDAO sessionDAO = new SessionDAO();

    public SessionService() {
    }

   public Session findSession(String sessionId){
        return sessionDAO.findSessionId(sessionId);
   }

   public Session findById(long id){
        return sessionDAO.findById(id);
   }
   public void saveSession(Session s){
        sessionDAO.save(s);
   }
   public void deleteSession(Session s){
        sessionDAO.delete(s);
   }
   public void updateSession(Session s){
        sessionDAO.update(s);
   }
}
