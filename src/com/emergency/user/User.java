package com.emergency.user;

/**
 * Stores basic information of a registered user.
 */
public class User {
    private String userId;
    private String fullName;
    private String phoneNumber;
    private String city;
    private EmergencyContact emergencyContact;

    public User(String userId, String fullName, String phoneNumber, String city, EmergencyContact emergencyContact) {
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.emergencyContact = emergencyContact;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public void displayUserDetails() {
        System.out.println("User ID          : " + userId);
        System.out.println("Full Name        : " + fullName);
        System.out.println("Phone Number     : " + phoneNumber);
        System.out.println("City             : " + city);
        if (emergencyContact != null) {
            System.out.println("Emergency Contact: " + emergencyContact.toString());
        }
    }

    @Override
    public String toString() {
        return "[" + userId + "] " + fullName + " (" + phoneNumber + ", " + city + ")";
    }
}
