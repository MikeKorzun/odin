package com.setplex.odin.controller.exception;

/**
 * Thrown when processing data is not logically valid
 */
public class InvalidDataException extends ApplicationException {

    public InvalidDataException(String message) {
        super(message);
    }
}
