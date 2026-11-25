package com.emergency.user;

import java.util.ArrayList;
import com.emergency.database.UserDatabase;
import com.emergency.exceptions.DuplicateUserException;

public class UserManager {
    private UserDatabase userDatabase;
    private UserRegistration userRegistration;
    private UserLogin userLogin;

    public UserManager(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
        this.userRegistration = new UserRegistration(userDatabase);
        this.userLogin = new UserLogin(userDatabase);
    }

    public User registerUser(String userId, String fullName, String phone, String city,
                             String contactName, String relationship, String contactPhone)
            throws DuplicateUserException {
        return userRegistration.registerNewUser(userId, fullName, phone, city, contactName, relationship, contactPhone);
    }

    public User findUser(String userId) {
        return userLogin.getUser(userId);
    }

    public void displayAllUsers() {
        ArrayList<User> users = userDatabase.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No registered users found.");
            return;
        }

        System.out.println("========== REGISTERED USERS (" + users.size() + ") ==========");
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            System.out.println((i + 1) + ". ID: " + u.getUserId() + " | Name: " + u.getFullName() +
                    " | Phone: " + u.getPhoneNumber() + " | City: " + u.getCity());
            if (u.getEmergencyContact() != null) {
                System.out.println("   Emergency Contact: " + u.getEmergencyContact().toString());
            }
        }
        System.out.println("===========================================");
    }

    public UserDatabase getUserDatabase() {
        return userDatabase;
    }
}
