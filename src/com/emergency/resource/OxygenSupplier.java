package com.emergency.resource;

/**
 * Represents an oxygen cylinder supplier or oxygen bank.
 */
public class OxygenSupplier extends Resource {
    private double cylinderCapacityLiters;
    private int availableCylinders;

    public OxygenSupplier(String resourceId, String name, String contactNumber, String city,
                          double cylinderCapacityLiters, int availableCylinders) {
        super(resourceId, name, contactNumber, city, "OXYGEN_SUPPLIER");
        this.cylinderCapacityLiters = cylinderCapacityLiters;
        this.availableCylinders = availableCylinders;
    }

    public double getCylinderCapacityLiters() {
        return cylinderCapacityLiters;
    }

    public void setCylinderCapacityLiters(double cylinderCapacityLiters) {
        this.cylinderCapacityLiters = cylinderCapacityLiters;
    }

    public int getAvailableCylinders() {
        return availableCylinders;
    }

    public void setAvailableCylinders(int availableCylinders) {
        this.availableCylinders = availableCylinders;
        if (this.availableCylinders <= 0) {
            this.isAvailable = false;
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Capacity  : " + cylinderCapacityLiters + " Liters");
        System.out.println("Cylinders : " + availableCylinders + " available");
    }

    @Override
    public String toString() {
        return super.toString() + " | Capacity: " + cylinderCapacityLiters + "L | Cylinders: " + availableCylinders;
    }
}
