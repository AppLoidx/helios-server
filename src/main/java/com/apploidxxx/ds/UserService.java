package com.apploidxxx.ds;

import com.apploidxxx.entity.User;
import com.apploidxxx.entity.dao.UsersDAO;

import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class UserService {
    private UsersDAO usersDao = new UsersDAO();

    public UserService() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

}
