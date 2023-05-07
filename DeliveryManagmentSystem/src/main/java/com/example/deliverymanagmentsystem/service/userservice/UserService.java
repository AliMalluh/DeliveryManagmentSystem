package com.example.deliverymanagmentsystem.service.userservice;

import com.example.deliverymanagmentsystem.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User add(User user);

    public User update(User user);

    public Optional<User> get(long id);

    public List<User> getAll() ;

    public void delete(long id);
}
