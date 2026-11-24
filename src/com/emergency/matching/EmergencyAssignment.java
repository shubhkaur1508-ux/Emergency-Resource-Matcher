package com.emergency.matching;

import com.emergency.request.EmergencyRequest;
import com.emergency.resource.Resource;

/**
 * Represents the final assignment link between an emergency request
 * and a successfully matched emergency resource.
 */
public class EmergencyAssignment {
    private EmergencyRequest request;
    private Resource matchedResource;
    private String assignmentId;
    private String timestamp;
    private String assignmentNotes;

    public EmergencyAssignment(String assignmentId, EmergencyRequest request, Resource matchedResource, String assignmentNotes) {
        this.assignmentId = assignmentId;
        this.request = request;
        this.matchedResource = matchedResource;
        this.assignmentNotes = assignmentNotes;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public EmergencyRequest getRequest() {
        return request;
    }

    public Resource getMatchedResource() {
        return matchedResource;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getAssignmentNotes() {
        return assignmentNotes;
    }

    public void displayAssignmentSummary() {
        System.out.println("==================================================");
        System.out.println("          EMERGENCY RESOURCE MATCH FOUND!         ");
        System.out.println("==================================================");
        System.out.println("Assignment ID   : " + assignmentId);
        System.out.println("Timestamp       : " + timestamp);
        System.out.println("Notes           : " + assignmentNotes);
        System.out.println("--- REQUEST DETAILS ---");
        request.displayRequestDetails();
        System.out.println("--- MATCHED RESOURCE DETAILS ---");
        matchedResource.displayDetails();
        System.out.println("==================================================");
    }
}
