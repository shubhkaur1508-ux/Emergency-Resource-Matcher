package com.emergency.exceptions;

/**
 * Custom Exception thrown when an emergency request has invalid
 * or incomplete information.
 */
public class InvalidRequestException extends Exception {

    public InvalidRequestException(String message) {
        super(message);
    }
}
