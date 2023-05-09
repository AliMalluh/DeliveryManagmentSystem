package com.example.deliverymanagmentsystem.model.user;

import com.example.deliverymanagmentsystem.model.Store;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//import javax.validation.Validator;
//import javax.validation.constraints.NotNull;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    private long id;
    @NotEmpty
    private String firstname;
    private String lastname;
    private Roles role;
    private Long storeId;
    private Store store;

}

