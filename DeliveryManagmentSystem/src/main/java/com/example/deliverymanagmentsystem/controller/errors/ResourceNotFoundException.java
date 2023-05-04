package com.example.deliverymanagmentsystem.controller.errors;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
