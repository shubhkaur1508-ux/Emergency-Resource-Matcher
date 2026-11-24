package com.emergency.matching;

import java.util.ArrayList;
import com.emergency.database.ResourceDatabase;
import com.emergency.request.BloodRequest;
import com.emergency.resource.BloodDonor;
import com.emergency.resource.Resource;

/**
 * Handles compatibility and matching logic for blood donor requests.
 */
public class BloodMatcher {

    public BloodDonor matchBloodDonor(BloodRequest request, ResourceDatabase db) {
        ArrayList<Resource> donors = db.getResourcesByType("BLOOD_DONOR");
        String requiredGroup = request.getRequiredBloodGroup().toUpperCase();
        String city = request.getCity();

        // 1st Priority: Exact blood group match in the same city
        for (int i = 0; i < donors.size(); i++) {
            BloodDonor donor = (BloodDonor) donors.get(i);
            if (donor.isAvailable() && donor.getBloodGroup().equalsIgnoreCase(requiredGroup)
                    && donor.getCity().equalsIgnoreCase(city)) {
                return donor;
            }
        }

        // 2nd Priority: Compatible blood group in the same city
        for (int i = 0; i < donors.size(); i++) {
            BloodDonor donor = (BloodDonor) donors.get(i);
            if (donor.isAvailable() && donor.getCity().equalsIgnoreCase(city)
                    && isCompatible(donor.getBloodGroup(), requiredGroup)) {
                return donor;
            }
        }

        // 3rd Priority: Compatible blood group in any city
        for (int i = 0; i < donors.size(); i++) {
            BloodDonor donor = (BloodDonor) donors.get(i);
            if (donor.isAvailable() && isCompatible(donor.getBloodGroup(), requiredGroup)) {
                return donor;
            }
        }

        return null;
    }

    public boolean isCompatible(String donorGroup, String recipientGroup) {
        donorGroup = donorGroup.trim().toUpperCase();
        recipientGroup = recipientGroup.trim().toUpperCase();

        if (donorGroup.equals(recipientGroup)) {
            return true;
        }

        // Universal donor: O- can donate to anyone
        if (donorGroup.equals("O-")) {
            return true;
        }

        // O+ can donate to O+, A+, B+, AB+
        if (donorGroup.equals("O+") && (recipientGroup.equals("A+") || recipientGroup.equals("B+") || recipientGroup.equals("AB+"))) {
            return true;
        }

        // A- can donate to A-, A+, AB-, AB+
        if (donorGroup.equals("A-") && (recipientGroup.equals("A+") || recipientGroup.equals("AB-") || recipientGroup.equals("AB+"))) {
            return true;
        }

        // B- can donate to B-, B+, AB-, AB+
        if (donorGroup.equals("B-") && (recipientGroup.equals("B+") || recipientGroup.equals("AB-") || recipientGroup.equals("AB+"))) {
            return true;
        }

        return false;
    }
}
