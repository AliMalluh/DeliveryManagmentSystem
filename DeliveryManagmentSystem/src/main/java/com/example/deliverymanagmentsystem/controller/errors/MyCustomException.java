package com.example.deliverymanagmentsystem.controller.errors;

public class MyCustomException extends RuntimeException {
    private String errorCode;

    public MyCustomException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}