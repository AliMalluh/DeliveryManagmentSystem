package com.example.deliverymanagmentsystem.controller;

import com.example.deliverymanagmentsystem.model.Store;
import com.example.deliverymanagmentsystem.service.storeservice.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity add(@RequestBody Store store) {
        storeService.add(store);
        return new ResponseEntity(store, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> get(@PathVariable Long id) {
        Store store = storeService.get(id);
        return new ResponseEntity(store, HttpStatus.OK);
    }
}
