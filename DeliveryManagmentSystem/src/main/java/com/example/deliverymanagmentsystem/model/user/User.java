package com.example.deliverymanagmentsystem.model.user;

import com.example.deliverymanagmentsystem.model.Store;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private long id;
    @NotEmpty
    private String firstname;
    private String lastname;
    private Roles role;
    private Long storeId;
    private Store store;
    private String userName;
    private String password;

}

