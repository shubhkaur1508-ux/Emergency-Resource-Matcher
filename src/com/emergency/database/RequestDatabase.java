package com.emergency.database;

import java.util.LinkedList;
import com.emergency.request.EmergencyRequest;

/**
 * In-memory storage layer using LinkedList collection to maintain the
 * emergency request queue and process requests in order (FIFO).
 */
public class RequestDatabase {
    private LinkedList<EmergencyRequest> requestQueue;

    public RequestDatabase() {
        this.requestQueue = new LinkedList<EmergencyRequest>();
    }

    public void enqueueRequest(EmergencyRequest request) {
        if (request != null) {
            requestQueue.addLast(request);
        }
    }

    public EmergencyRequest dequeueRequest() {
        if (requestQueue.isEmpty()) {
            return null;
        }
        return requestQueue.removeFirst();
    }

    public EmergencyRequest peekRequest() {
        return requestQueue.peekFirst();
    }

    public LinkedList<EmergencyRequest> getAllPendingRequests() {
        return requestQueue;
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }

    public int getPendingCount() {
        return requestQueue.size();
    }
}
