package com.example.deliverymanagmentsystem.dao;

import com.example.deliverymanagmentsystem.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepo {
    User save(User user);

    User update(User user);

    Optional<User> getById(long id);

    List<User> getAll();

    void deleteById(long id);
}
