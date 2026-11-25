package com.emergency.threads;

import com.emergency.matching.EmergencyAssignment;
import com.emergency.request.EmergencyRequest;
import com.emergency.resource.Resource;

/**
 * Demonstrates Multithreading Approach 1: Extending the Thread class.
 * Simulates asynchronous SMS, call, and push notifications to both the
 * requester and the matched resource contact.
 */
public class NotificationThread extends Thread {
    private EmergencyAssignment assignment;

    public NotificationThread(EmergencyAssignment assignment) {
        super("NotificationWorkerThread");
        this.assignment = assignment;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n>>> [Thread: " + getName() + "] INITIATING EMERGENCY NOTIFICATIONS...");
            Thread.sleep(400); // Simulate network latency for alert broadcast

            EmergencyRequest req = assignment.getRequest();
            Resource res = assignment.getMatchedResource();

            System.out.println(">>> [ALERT SENT] SMS to Requester " + req.getRequesterName() + " (" + req.getContactNumber() + "):");
            System.out.println("    \"Help is on the way! Matched " + res.getName() + " (" + res.getContactNumber() + "). ID: " + assignment.getAssignmentId() + "\"");

            Thread.sleep(200);

            System.out.println(">>> [ALERT SENT] SMS to Resource Provider " + res.getName() + " (" + res.getContactNumber() + "):");
            System.out.println("    \"Emergency task assigned! Contact requester " + req.getRequesterName() + " (" + req.getContactNumber() + ") at " + req.getCity() + "\"");

            System.out.println(">>> [Thread: " + getName() + "] ALL NOTIFICATIONS DISPATCHED SUCCESSFULLY! <<<\n");
        } catch (InterruptedException e) {
            System.out.println(">>> [Thread: " + getName() + "] Notification interrupted: " + e.getMessage());
        }
    }
}
