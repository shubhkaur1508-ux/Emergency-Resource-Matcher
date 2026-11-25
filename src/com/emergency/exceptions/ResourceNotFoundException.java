package com.emergency.exceptions;

/**
 * Custom Exception thrown when no matching resource is found
 * for an emergency request.
 */
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
