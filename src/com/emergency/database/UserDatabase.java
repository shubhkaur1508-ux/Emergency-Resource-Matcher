package com.emergency.database;

import java.util.ArrayList;
import com.emergency.user.User;

public class UserDatabase {
    private ArrayList<User> userList;

    public UserDatabase() {
        this.userList = new ArrayList<User>();
    }

    public void addUser(User user) {
        if (user != null) {
            userList.add(user);
        }
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public User findUserById(String userId) {
        for (int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            if (u.getUserId().equalsIgnoreCase(userId)) {
                return u;
            }
        }
        return null;
    }

    public boolean isUserExists(String userId) {
        return findUserById(userId) != null;
    }

    public int getUserCount() {
        return userList.size();
    }
}
