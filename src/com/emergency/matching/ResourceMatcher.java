package com.emergency.matching;

import java.util.ArrayList;
import com.emergency.database.ResourceDatabase;
import com.emergency.exceptions.ResourceNotFoundException;
import com.emergency.request.AmbulanceRequest;
import com.emergency.request.BloodRequest;
import com.emergency.request.EmergencyRequest;
import com.emergency.request.OxygenRequest;
import com.emergency.request.VolunteerRequest;
import com.emergency.resource.Ambulance;
import com.emergency.resource.BloodDonor;
import com.emergency.resource.OxygenSupplier;
import com.emergency.resource.Resource;
import com.emergency.resource.Volunteer;

/**
 * General resource matcher coordinating matching for all resource types.
 */
public class ResourceMatcher {
    private ResourceDatabase resourceDatabase;
    private BloodMatcher bloodMatcher;
    private OxygenMatcher oxygenMatcher;
    private int assignmentCounter;

    public ResourceMatcher(ResourceDatabase resourceDatabase) {
        this.resourceDatabase = resourceDatabase;
        this.bloodMatcher = new BloodMatcher();
        this.oxygenMatcher = new OxygenMatcher();
        this.assignmentCounter = 1000;
    }

    public EmergencyAssignment match(EmergencyRequest request) throws ResourceNotFoundException {
        if (request == null) {
            throw new ResourceNotFoundException("Cannot match null emergency request.");
        }

        Resource matched = null;
        String notes = "";

        if (request instanceof BloodRequest) {
            BloodDonor donor = bloodMatcher.matchBloodDonor((BloodRequest) request, resourceDatabase);
            if (donor != null) {
                matched = donor;
                notes = "Matched donor blood group " + donor.getBloodGroup() + " for request";
            }
        } else if (request instanceof OxygenRequest) {
            OxygenSupplier supplier = oxygenMatcher.matchOxygenSupplier((OxygenRequest) request, resourceDatabase);
            if (supplier != null) {
                matched = supplier;
                notes = "Matched oxygen supplier with " + supplier.getAvailableCylinders() + " cylinders";
            }
        } else if (request instanceof AmbulanceRequest) {
            matched = findAvailableAmbulance(request.getCity());
            if (matched != null) {
                notes = "Ambulance dispatched for patient condition";
            }
        } else if (request instanceof VolunteerRequest) {
            matched = findAvailableVolunteer(request.getCity());
            if (matched != null) {
                notes = "Volunteer assigned for emergency field support";
            }
        }

        if (matched == null) {
            throw new ResourceNotFoundException("No available " + request.getRequestType() + " resources found for Request ID: " + request.getRequestId());
        }

        assignmentCounter++;
        String assignmentId = "ASN-" + assignmentCounter;
        return new EmergencyAssignment(assignmentId, request, matched, notes);
    }

    private Ambulance findAvailableAmbulance(String city) {
        ArrayList<Resource> list = resourceDatabase.getResourcesByType("AMBULANCE");
        // 1st Priority: same city
        for (int i = 0; i < list.size(); i++) {
            Ambulance amb = (Ambulance) list.get(i);
            if (amb.isAvailable() && amb.getCity().equalsIgnoreCase(city)) {
                return amb;
            }
        }
        // 2nd Priority: any available
        for (int i = 0; i < list.size(); i++) {
            Ambulance amb = (Ambulance) list.get(i);
            if (amb.isAvailable()) {
                return amb;
            }
        }
        return null;
    }

    private Volunteer findAvailableVolunteer(String city) {
        ArrayList<Resource> list = resourceDatabase.getResourcesByType("VOLUNTEER");
        for (int i = 0; i < list.size(); i++) {
            Volunteer vol = (Volunteer) list.get(i);
            if (vol.isAvailable() && vol.getCity().equalsIgnoreCase(city)) {
                return vol;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Volunteer vol = (Volunteer) list.get(i);
            if (vol.isAvailable()) {
                return vol;
            }
        }
        return null;
    }

    public ResourceDatabase getResourceDatabase() {
        return resourceDatabase;
    }
}
