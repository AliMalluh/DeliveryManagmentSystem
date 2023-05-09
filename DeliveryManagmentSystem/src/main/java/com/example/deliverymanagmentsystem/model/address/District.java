package com.example.deliverymanagmentsystem.model.address;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.validation.constraints.NotNull;

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
