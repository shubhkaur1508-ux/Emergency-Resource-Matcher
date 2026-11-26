package com.emergency.resource;

/**
 * Represents an on-ground emergency response volunteer.
 */
public class Volunteer extends Resource {
    private String skillArea; // e.g., "First Aid", "Disaster Relief", "Logistics"
    private String availabilityHours; // e.g., "24/7", "Day Shift", "Night Shift"

    public Volunteer(String resourceId, String name, String contactNumber, String city,
                     String skillArea, String availabilityHours) {
        super(resourceId, name, contactNumber, city, "VOLUNTEER");
        this.skillArea = skillArea;
        this.availabilityHours = availabilityHours;
    }

    public String getSkillArea() {
        return skillArea;
    }

    public void setSkillArea(String skillArea) {
        this.skillArea = skillArea;
    }

    public String getAvailabilityHours() {
        return availabilityHours;
    }

    public void setAvailabilityHours(String availabilityHours) {
        this.availabilityHours = availabilityHours;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Skill     : " + skillArea);
        System.out.println("Shift     : " + availabilityHours);
    }

    @Override
    public String toString() {
        return super.toString() + " | Skill: " + skillArea + " | Shift: " + availabilityHours;
    }
}
