package com.setplex.odin.controller.exception;

import lombok.Getter;

/**
 * Base exception for the whole Odin application
 */
public class ApplicationException extends RuntimeException {
    @Getter
    private String userMessage;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable throwable) {
        super(throwable);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(String message, String userMessage) {
        super(message);
        this.userMessage = userMessage;
    }

    public ApplicationException(String message, String userMessage, Throwable cause) {
        super(message, cause);
        this.userMessage = userMessage;
    }
}
