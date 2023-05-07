package com.example.deliverymanagmentsystem.service.storeservice;

import com.example.deliverymanagmentsystem.model.Store;

public interface StoreService {
    public Store add(Store store);
    public Store get(Long id);
}
