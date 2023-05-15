package com.example.deliverymanagmentsystem.model.user;

public enum Roles {
    ADMIN("Admin"), EMPLOYEE("Employee"), CLIENT("Client");

    private String displayName;
    Roles(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
