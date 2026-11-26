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

        UserValidator.validateUserDetails(userId, fullName, phone, city, contactName, relationship, contactPhone);

        String normalizedId = userId.trim();
        if (userDatabase.isUserExists(normalizedId)) {
            throw new DuplicateUserException("User with ID '" + normalizedId + "' already exists!");
        }

        EmergencyContact contact = new EmergencyContact(contactName.trim(), relationship.trim(), contactPhone.trim());
        User newUser = new User(normalizedId, fullName.trim(), phone.trim(), city.trim(), contact);
        userDatabase.addUser(newUser);
        return newUser;
    }
}
