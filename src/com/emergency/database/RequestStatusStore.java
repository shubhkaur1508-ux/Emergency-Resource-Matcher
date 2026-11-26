package com.emergency.database;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory storage layer using Map collection to store request IDs
 * and their corresponding lifecycle statuses.
 */
public class RequestStatusStore {
    private Map<String, String> statusMap;

    public RequestStatusStore() {
        this.statusMap = new HashMap<String, String>();
    }

    public void updateStatus(String requestId, String status) {
        if (requestId != null && status != null) {
            statusMap.put(requestId.toUpperCase(), status);
        }
    }

    public String getStatus(String requestId) {
        if (requestId == null) {
            return "UNKNOWN";
        }
        String status = statusMap.get(requestId.toUpperCase());
        return (status != null) ? status : "NOT_FOUND";
    }

    public Map<String, String> getAllStatuses() {
        return statusMap;
    }

    public void displayAllStatuses() {
        if (statusMap.isEmpty()) {
            System.out.println("No request status records available.");
            return;
        }

        System.out.println("========== REQUEST STATUS STORE (Map) ==========");
        for (Map.Entry<String, String> entry : statusMap.entrySet()) {
            System.out.println("Request ID: " + entry.getKey() + " => Status: " + entry.getValue());
        }
        System.out.println("================================================");
    }
}
