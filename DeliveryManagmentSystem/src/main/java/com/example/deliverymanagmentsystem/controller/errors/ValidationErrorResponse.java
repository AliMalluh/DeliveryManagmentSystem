package com.example.deliverymanagmentsystem.controller.errors;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ValidationErrorResponse {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ValidationErrorResponse(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
