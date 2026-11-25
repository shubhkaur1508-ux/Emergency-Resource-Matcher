package com.emergency.exceptions;

/**
 * Custom Exception thrown when an attempt is made to register
 * a resource with an ID that already exists.
 */
public class DuplicateResourceException extends Exception {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
