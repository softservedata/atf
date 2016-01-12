package com.softserve.edu.oms.data;

import com.softserve.edu.entity.UserDB;

public class UserUtils {
    private static volatile UserUtils instance = null;

    private UserUtils() {
    }

    public static UserUtils get() {
        if (instance == null) {
            synchronized (UserUtils.class) {
                if (instance == null) {
                    instance = new UserUtils();
                }
            }
        }
        return instance;
    }

    public IUser userDB2IUser(UserDB userDB) {
        return User.get()
                .setFirstname(userDB.getFirstname())
                .setLastname(userDB.getLastname())
                .setLogin(userDB.getLogin())
                .setPassword(userDB.getPassword())
                .setEmail(userDB.getEmail())
                .setRegion("West")
                .setRole("Administrator")
                .build();
    }

}