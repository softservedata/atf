package com.softserve.edu.service;

import com.softserve.edu.dao.DataSource;
import com.softserve.edu.dao.UserDao;
import com.softserve.edu.entity.UserDB;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserUtils;


public class UserService {
    private static volatile UserService instance = null;
    private UserDao userDao;

    private UserService(DataSource dataSource) {
        userDao = UserDao.get(dataSource);
    }

    public static UserService get() {
        if (instance == null) {
        	throw new RuntimeException("Error");
        }
        return instance;
    }

    public static UserService get(DataSource dataSource) {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService(dataSource);
                }
            }
        }
        return instance;
    }

    public boolean insertUser(IUser user) {
    	return userDao.insertUser(user.getLogin(), user.getPassword(),
    			user.getFirstname(), user.getLastname(), user.getEmail(), 1L, 1L);
    }

    public IUser getUserByLogin(String login) {
        UserDB userDB = userDao.getUserByLogin(login);
        return UserUtils.get().userDB2IUser(userDB);
    }

    public String getUserFirstnameByLogin(String login) {
        UserDB userDB = userDao.getUserByLogin(login);
        return userDB.getFirstname();
    }

    public boolean deleteUser(IUser user) {
    	UserDB userDB = userDao.getUserByLogin(user.getLogin());
        return userDao.deleteUserDB(userDB);
    }

    public boolean deleteUsersByPartialLogin(String partialLogin) {
        return userDao.deleteUsersByPartialLogin(partialLogin);
    }

}