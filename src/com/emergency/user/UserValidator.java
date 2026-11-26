package com.emergency.user;

public class UserValidator {

    public static void validateUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty.");
        }
    }

    public static void validatePhoneNumber(String phoneNumber, String label) {
        if (phoneNumber == null || !phoneNumber.trim().matches("\\d{7,15}")) {
            throw new IllegalArgumentException(label + " must contain 7 to 15 digits.");
        }
    }

    public static void validateUserDetails(String userId, String fullName, String phone, String city,
                                           String contactName, String relationship, String contactPhone) {
        validateUserId(userId);
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty.");
        }
        validatePhoneNumber(phone, "Phone number");
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty.");
        }
        if (contactName == null || contactName.trim().isEmpty()) {
            throw new IllegalArgumentException("Emergency contact name cannot be empty.");
        }
        if (relationship == null || relationship.trim().isEmpty()) {
            throw new IllegalArgumentException("Emergency contact relationship cannot be empty.");
        }
        validatePhoneNumber(contactPhone, "Emergency contact phone");
    }
}
