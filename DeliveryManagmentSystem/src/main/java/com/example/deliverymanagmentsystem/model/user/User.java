package com.example.deliverymanagmentsystem.model.user;

import com.example.deliverymanagmentsystem.model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String firstname;
    private String lastname;
    private Roles role;
    private long storeId;
    private Store store;

}

