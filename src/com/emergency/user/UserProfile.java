package com.emergency.user;

/**
 * Formats and displays detailed user profile information.
 */
public class UserProfile {

    public static void printProfile(User user) {
        if (user == null) {
            System.out.println("No user profile to display.");
            return;
        }
        System.out.println("----------------------------------------");
        System.out.println("            USER PROFILE                ");
        System.out.println("----------------------------------------");
        user.displayUserDetails();
        System.out.println("----------------------------------------");
    }
}
