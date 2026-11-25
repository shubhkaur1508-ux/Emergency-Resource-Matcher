package com.emergency.threads;

import com.emergency.database.RequestDatabase;
import com.emergency.database.RequestStatusStore;
import com.emergency.matching.EmergencyAssignment;
import com.emergency.matching.ResourceMatcher;
import com.emergency.request.EmergencyRequest;
import com.emergency.request.OxygenRequest;
import com.emergency.request.RequestHistory;
import com.emergency.resource.OxygenSupplier;
import com.emergency.resource.Resource;

/**
 * Coordinates simulated multi-threaded emergency request processing.
 * Dequeues requests from LinkedList queue, executes MatchingTask via Runnable thread,
 * updates status in Map store, and spawns NotificationThread (extending Thread) for alerts.
 */
public class RequestProcessor {
    private RequestDatabase requestDatabase;
    private RequestStatusStore requestStatusStore;
    private RequestHistory requestHistory;
    private ResourceMatcher resourceMatcher;

    public RequestProcessor(RequestDatabase requestDatabase, RequestStatusStore requestStatusStore,
                            RequestHistory requestHistory, ResourceMatcher resourceMatcher) {
        this.requestDatabase = requestDatabase;
        this.requestStatusStore = requestStatusStore;
        this.requestHistory = requestHistory;
        this.resourceMatcher = resourceMatcher;
    }

    public void processNextPendingRequest() {
        if (!requestDatabase.hasPendingRequests()) {
            System.out.println("No pending requests in the LinkedList queue to process.");
            return;
        }

        // 1. Dequeue request from LinkedList
        EmergencyRequest request = requestDatabase.dequeueRequest();
        System.out.println("Processing next queued request: " + request.getRequestId() + " (" + request.getRequestType() + ")...");
        requestStatusStore.updateStatus(request.getRequestId(), "PROCESSING");

        // 2. Create Runnable task
        MatchingTask task = new MatchingTask(request, resourceMatcher);

        // 3. Create Thread object and start it
        Thread workerThread = new Thread(task, "MatchingWorkerThread");
        workerThread.start();

        try {
            // Wait for matching thread to finish
            workerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution error: " + e.getMessage());
        }

        // 4. Handle results
        if (task.isSuccess() && task.getAssignmentResult() != null) {
            EmergencyAssignment assignment = task.getAssignmentResult();

            // Display assignment
            assignment.displayAssignmentSummary();

            // Mark resource as assigned
            Resource matchedResource = assignment.getMatchedResource();
            if (matchedResource instanceof OxygenSupplier) {
                OxygenSupplier supplier = (OxygenSupplier) matchedResource;
                supplier.setAvailableCylinders(supplier.getAvailableCylinders() - 1);
            } else {
                matchedResource.setAvailable(false);
            }

            // Update request status
            request.setStatus("MATCHED");
            requestStatusStore.updateStatus(request.getRequestId(), "MATCHED (Assigned to: " + matchedResource.getName() + " - " + matchedResource.getResourceId() + ")");
            requestHistory.addCompletedRequest(request);

            // 5. Trigger notification using Thread class (Approach 1)
            NotificationThread notifyThread = new NotificationThread(assignment);
            notifyThread.start();

            try {
                notifyThread.join();
            } catch (InterruptedException e) {
                System.out.println("Notification wait interrupted: " + e.getMessage());
            }

        } else {
            // Unmatched
            request.setStatus("UNMATCHED");
            requestStatusStore.updateStatus(request.getRequestId(), "UNMATCHED (Resource unavailable)");
            requestHistory.addCompletedRequest(request);
            System.out.println("Matching failed: " + (task.getNotFoundException() != null ? task.getNotFoundException().getMessage() : "Resource not found"));
        }
    }
}
