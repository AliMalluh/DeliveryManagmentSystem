package com.example.deliverymanagmentsystem.controller.errors;

public class ResourceNotFoundException extends RuntimeException {
    private final String errorCode;

    public ResourceNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
