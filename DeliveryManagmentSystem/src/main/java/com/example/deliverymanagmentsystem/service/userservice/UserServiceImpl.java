package com.example.deliverymanagmentsystem.service.userservice;

import com.example.deliverymanagmentsystem.dao.userrepo.UserRepo;
import com.example.deliverymanagmentsystem.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public User add(User user) {
        return userRepo.save(user);
    }

    public User update(User user) {
        return userRepo.update(user);
    }

    public Optional<User> get(long id) {
        return userRepo.getById(id);
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    @Override
    public void delete(long id) {
         userRepo.deleteById(id);
    }
}
