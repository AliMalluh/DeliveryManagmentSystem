package com.example.deliverymanagmentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DeliveryManagmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryManagmentSystemApplication.class, args);
    }

}
