package com.example.deliverymanagmentsystem.model;

import com.example.deliverymanagmentsystem.model.address.Address;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    private long id;
    @NotNull
    private String name;
    @NotNull
    private Address address;
}
