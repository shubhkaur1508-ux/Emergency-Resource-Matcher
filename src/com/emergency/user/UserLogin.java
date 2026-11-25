package com.emergency.user;

import com.emergency.database.UserDatabase;

/**
 * Validates and authenticates user login details using stored users.
 */
public class UserLogin {
    private UserDatabase userDatabase;

    public UserLogin(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public boolean authenticate(String userId) {
        return userDatabase.isUserExists(userId);
    }

    public User getUser(String userId) {
        return userDatabase.findUserById(userId);
    }
}
