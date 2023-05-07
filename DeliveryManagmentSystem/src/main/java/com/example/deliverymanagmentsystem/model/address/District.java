package com.example.deliverymanagmentsystem.model.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {
    @NotNull
    private long districtId;
    @NotNull
    private String districtName;
    @NotNull
    private String street;
    @NotNull
    private String zipCode;
}
