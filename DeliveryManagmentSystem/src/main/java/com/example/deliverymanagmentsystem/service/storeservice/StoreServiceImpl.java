package com.example.deliverymanagmentsystem.service.storeservice;

import com.example.deliverymanagmentsystem.dao.storerepo.StoreRepo;
import com.example.deliverymanagmentsystem.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService{
    @Autowired
    private StoreRepo storeRepo;
    @Override
    public Store add(Store store) {
        return storeRepo.save(store);
    }

    @Override
    public Store get(Long id) {
        return storeRepo.findById(id);
    }
}
