package com.emergency.user;

import java.util.HashMap;
import java.util.Map;
import com.emergency.database.ResourceDatabase;
import com.emergency.resource.Resource;

public class AdminVerification {
    private ResourceDatabase resourceDatabase;
    private Map<String, String> verificationStatusMap;

    public AdminVerification(ResourceDatabase resourceDatabase) {
        this.resourceDatabase = resourceDatabase;
        this.verificationStatusMap = new HashMap<String, String>();
    }

    public boolean approveProvider(String resourceId) {
        if (resourceId == null || resourceId.trim().isEmpty() || resourceDatabase == null) {
            return false;
        }
        Resource resource = resourceDatabase.findResourceById(resourceId.trim());
        if (resource == null) {
            return false;
        }
        resource.setAvailable(true);
        verificationStatusMap.put(resource.getResourceId().toUpperCase(), "APPROVED");
        return true;
    }

    public boolean rejectProvider(String resourceId) {
        if (resourceId == null || resourceId.trim().isEmpty() || resourceDatabase == null) {
            return false;
        }
        Resource resource = resourceDatabase.findResourceById(resourceId.trim());
        if (resource == null) {
            return false;
        }
        resource.setAvailable(false);
        verificationStatusMap.put(resource.getResourceId().toUpperCase(), "REJECTED");
        return true;
    }

    public String getVerificationStatus(String resourceId) {
        if (resourceId == null || resourceId.trim().isEmpty()) {
            return "UNKNOWN";
        }
        return verificationStatusMap.getOrDefault(resourceId.trim().toUpperCase(), "PENDING");
    }

    public boolean isApproved(String resourceId) {
        return "APPROVED".equals(getVerificationStatus(resourceId));
    }

    public void displayVerificationStatuses() {
        if (verificationStatusMap.isEmpty()) {
            System.out.println("No provider verification records available.");
            return;
        }
        System.out.println("========== ADMIN PROVIDER VERIFICATION ==========");
        for (Map.Entry<String, String> entry : verificationStatusMap.entrySet()) {
            System.out.println("Provider ID: " + entry.getKey() + " => Status: " + entry.getValue());
        }
        System.out.println("=================================================");
    }
}
