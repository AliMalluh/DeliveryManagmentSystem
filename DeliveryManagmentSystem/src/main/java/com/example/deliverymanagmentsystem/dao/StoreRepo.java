package com.example.deliverymanagmentsystem.dao;

import com.example.deliverymanagmentsystem.model.Store;

public interface StoreRepo {
    Store findById(Long id);
    Store save(Store store);
}
