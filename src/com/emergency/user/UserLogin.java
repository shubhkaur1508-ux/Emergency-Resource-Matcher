package com.emergency.user;

import com.emergency.database.UserDatabase;

public class UserLogin {
    private UserDatabase userDatabase;

    public UserLogin(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public boolean authenticate(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return false;
        }
        return userDatabase.isUserExists(userId.trim());
    }

    public User getUser(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return null;
        }
        return userDatabase.findUserById(userId.trim());
    }
}
