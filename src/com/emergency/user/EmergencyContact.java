package com.emergency.user;

public class EmergencyContact {
    private String contactName;
    private String relationship;
    private String contactNumber;

    public EmergencyContact(String contactName, String relationship, String contactNumber) {
        this.contactName = contactName;
        this.relationship = relationship;
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return contactName + " (" + relationship + ") - " + contactNumber;
    }
}
