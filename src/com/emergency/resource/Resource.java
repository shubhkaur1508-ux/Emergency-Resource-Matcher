package com.emergency.resource;

/**
 * Base class representing a generic emergency resource.
 */
public class Resource {
    protected String resourceId;
    protected String name;
    protected String contactNumber;
    protected String city;
    protected String resourceType;
    protected boolean isAvailable;

    public Resource(String resourceId, String name, String contactNumber, String city, String resourceType) {
        this.resourceId = resourceId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.city = city;
        this.resourceType = resourceType;
        this.isAvailable = true;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void displayDetails() {
        System.out.println("ID        : " + resourceId);
        System.out.println("Type      : " + resourceType);
        System.out.println("Name      : " + name);
        System.out.println("Contact   : " + contactNumber);
        System.out.println("City      : " + city);
        System.out.println("Status    : " + (isAvailable ? "AVAILABLE" : "OCCUPIED / ASSIGNED"));
    }

    @Override
    public String toString() {
        return "[" + resourceType + "] ID: " + resourceId + " | Name: " + name + " | City: " + city + " | Status: " + (isAvailable ? "Available" : "Occupied");
    }
}
