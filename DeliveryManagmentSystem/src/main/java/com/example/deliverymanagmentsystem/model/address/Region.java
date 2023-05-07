package com.example.deliverymanagmentsystem.model.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @NotNull
    private long regionId;
    @NotNull
    private String regionName;
}
