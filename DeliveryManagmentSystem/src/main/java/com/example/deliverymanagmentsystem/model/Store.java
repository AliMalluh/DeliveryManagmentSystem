package com.example.deliverymanagmentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @NotNull
    private long id;
    @NotNull
    private String name;
    @NotNull
    private long user;
    @NotNull
    private long address;
}
