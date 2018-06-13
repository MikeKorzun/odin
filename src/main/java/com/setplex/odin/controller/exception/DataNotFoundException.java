package com.setplex.odin.controller.exception;

/**
 * Thrown when underlying data is not present
 */
public class DataNotFoundException extends ApplicationException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(String message, String userMessage) {
        super(message,userMessage);
    }

    public DataNotFoundException(String message, String userMessage, Throwable cause) {
        super(message, userMessage, cause);
    }
}
