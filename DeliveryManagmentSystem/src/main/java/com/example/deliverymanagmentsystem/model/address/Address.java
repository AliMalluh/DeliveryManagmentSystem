package com.example.deliverymanagmentsystem.model.address;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotNull
    private Region region;
    @NotNull
    private District district;
}
