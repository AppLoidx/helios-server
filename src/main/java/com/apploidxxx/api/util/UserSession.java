package com.apploidxxx.api.util;

import com.apploidxxx.entity.Session;
import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.SessionService;

/**
 * @author Arthur Kupriyanov
 */
public class UserSession {


    public static User getUser(String sessionId){
        SessionService ss = new SessionService();
        Session userSession = ss.findSession(sessionId);
        User user;
        if (userSession==null){
            return null;
        } else {
            return userSession.getUser();
        }
    }
}
