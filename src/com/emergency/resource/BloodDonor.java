package com.emergency.resource;

/**
 * Represents a registered blood donor.
 */
public class BloodDonor extends Resource {
    private String bloodGroup;
    private int age;

    public BloodDonor(String resourceId, String name, String contactNumber, String city,
                      String bloodGroup, int age) {
        super(resourceId, name, contactNumber, city, "BLOOD_DONOR");
        this.bloodGroup = bloodGroup;
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Blood Grp : " + bloodGroup);
        System.out.println("Donor Age : " + age);
    }

    @Override
    public String toString() {
        return super.toString() + " | Blood Group: " + bloodGroup + " | Age: " + age;
    }
}
