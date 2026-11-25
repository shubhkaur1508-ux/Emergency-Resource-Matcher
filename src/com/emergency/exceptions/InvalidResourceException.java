package com.emergency.exceptions;

/**
 * Custom Exception thrown when resource details are invalid or missing.
 */
public class InvalidResourceException extends Exception {

    public InvalidResourceException(String message) {
        super(message);
    }
}
