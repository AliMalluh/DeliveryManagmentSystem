package com.example.deliverymanagmentsystem.service;

import com.example.deliverymanagmentsystem.model.Store;

public interface StoreService {
    public Store add(Store store);
    public Store get(Long id);
}
