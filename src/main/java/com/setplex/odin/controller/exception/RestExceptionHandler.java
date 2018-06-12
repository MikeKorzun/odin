package com.setplex.odin.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    /*@ExceptionHandler(ImageSearchException.class)
    public ResponseEntity<ErrorResponse> handle(ImageSearchException e) {
        return new ResponseEntity<> (new ErrorResponse(e.getErrorCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ImageSaveException.class)
    public ResponseEntity<ErrorResponse> handle(ImageSaveException e) {
        return new ResponseEntity<> (new ErrorResponse(e.getErrorCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
