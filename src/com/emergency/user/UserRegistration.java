package com.emergency.user;

import com.emergency.database.UserDatabase;
import com.emergency.exceptions.DuplicateUserException;

public class UserRegistration {
    private UserDatabase userDatabase;

    public UserRegistration(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public User registerNewUser(String userId, String fullName, String phone, String city,
                                String contactName, String relationship, String contactPhone)
            throws DuplicateUserException {

        if (userDatabase.isUserExists(userId)) {
            throw new DuplicateUserException("User with ID '" + userId + "' already exists!");
        }

        EmergencyContact contact = new EmergencyContact(contactName, relationship, contactPhone);
        User newUser = new User(userId, fullName, phone, city, contact);
        userDatabase.addUser(newUser);
        return newUser;
    }
}
