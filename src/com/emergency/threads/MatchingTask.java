package com.emergency.threads;

import com.emergency.exceptions.ResourceNotFoundException;
import com.emergency.matching.EmergencyAssignment;
import com.emergency.matching.ResourceMatcher;
import com.emergency.request.EmergencyRequest;

/**
 * Demonstrates Multithreading Approach 2: Implementing the Runnable interface.
 * Executes the emergency resource matching algorithm in a separate thread.
 */
public class MatchingTask implements Runnable {
    private EmergencyRequest request;
    private ResourceMatcher matcher;
    private EmergencyAssignment assignmentResult;
    private ResourceNotFoundException notFoundException;
    private boolean isSuccess;

    public MatchingTask(EmergencyRequest request, ResourceMatcher matcher) {
        this.request = request;
        this.matcher = matcher;
        this.isSuccess = false;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("\n*** [Thread: " + threadName + " (Runnable)] Starting Resource Search for Request: " + request.getRequestId() + " ***");

        try {
            // Simulate matching computation delay
            Thread.sleep(500);

            this.assignmentResult = matcher.match(request);
            this.isSuccess = true;
            System.out.println("*** [Thread: " + threadName + " (Runnable)] Match found successfully! ***");
        } catch (ResourceNotFoundException e) {
            this.notFoundException = e;
            this.isSuccess = false;
            System.out.println("*** [Thread: " + threadName + " (Runnable)] " + e.getMessage() + " ***");
        } catch (InterruptedException e) {
            System.out.println("*** [Thread: " + threadName + " (Runnable)] Matching task interrupted! ***");
        }
    }

    public EmergencyAssignment getAssignmentResult() {
        return assignmentResult;
    }

    public ResourceNotFoundException getNotFoundException() {
        return notFoundException;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public EmergencyRequest getRequest() {
        return request;
    }
}
