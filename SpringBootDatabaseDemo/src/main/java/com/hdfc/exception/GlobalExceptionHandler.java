package com.hdfc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> createErrorBody(HttpStatus status, String message) {

        Map<String, Object> body = new HashMap<>();

        body.put("Status", status.value());
        body.put("Message", message);

        return body;
    }

    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyExistsException(EmployeeAlreadyExistException e) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(createErrorBody(HttpStatus.CONFLICT, e.getMessage()));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEmployeeNotFoundException(EmployeeNotFoundException e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createErrorBody(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(createErrorBody(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}
