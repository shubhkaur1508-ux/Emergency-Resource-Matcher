package com.emergency.exceptions;

/**
 * Custom Exception thrown when an attempt is made to register
 * a user with an ID that already exists.
 */
public class DuplicateUserException extends Exception {

    public DuplicateUserException(String message) {
        super(message);
    }
}
