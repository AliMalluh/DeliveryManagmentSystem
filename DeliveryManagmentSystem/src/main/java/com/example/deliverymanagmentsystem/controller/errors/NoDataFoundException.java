package com.example.deliverymanagmentsystem.controller.errors;

public class NoDataFoundException extends RuntimeException{

    public NoDataFoundException(String message) {
        super(message);
    }
}