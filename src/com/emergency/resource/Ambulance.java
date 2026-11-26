package com.emergency.resource;

/**
 * Represents an emergency ambulance vehicle and service.
 */
public class Ambulance extends Resource {
    private String vehicleNumber;
    private String ambulanceType; // e.g. "Basic Life Support", "ICU Ambulance"

    public Ambulance(String resourceId, String name, String contactNumber, String city,
                     String vehicleNumber, String ambulanceType) {
        super(resourceId, name, contactNumber, city, "AMBULANCE");
        this.vehicleNumber = vehicleNumber;
        this.ambulanceType = ambulanceType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getAmbulanceType() {
        return ambulanceType;
    }

    public void setAmbulanceType(String ambulanceType) {
        this.ambulanceType = ambulanceType;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Vehicle No: " + vehicleNumber);
        System.out.println("Ambulance : " + ambulanceType);
    }

    @Override
    public String toString() {
        return super.toString() + " | Vehicle: " + vehicleNumber + " | Type: " + ambulanceType;
    }
}
