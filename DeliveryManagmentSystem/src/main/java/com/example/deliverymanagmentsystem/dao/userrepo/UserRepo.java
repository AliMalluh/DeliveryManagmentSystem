package com.example.deliverymanagmentsystem.dao.userrepo;

import com.example.deliverymanagmentsystem.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {
    User save(User user);

    User update(User user);

    Optional<User> getById(long id);

    List<User> getAll();

    void deleteById(long id);

    Optional<User> findByName(String username);
}
