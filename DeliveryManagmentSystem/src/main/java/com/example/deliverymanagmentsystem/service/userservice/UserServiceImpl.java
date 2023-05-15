package com.example.deliverymanagmentsystem.service.userservice;

import com.example.deliverymanagmentsystem.controller.errors.ResourceNotFoundException;
import com.example.deliverymanagmentsystem.dao.userrepo.UserRepo;
import com.example.deliverymanagmentsystem.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @CachePut(value="users")
    @Override
    public User add(User user) {
        if (!user.getRole().toString().equals("CLIENT")){
            user.setStoreId(null);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    @CachePut(value="users")
    @Override
    public User update(User user) {
        if (!user.getRole().toString().equals("CLIENT")){
            user.setStoreId(null);
        }
        return userRepo.update(user);
    }
    @Cacheable(value="users")
    @Override
    public User get(long id) {
        if(userRepo.getById(id).isEmpty()){
            System.out.println(HttpStatus.NOT_FOUND.name());
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.name(),"No Employee");
        }else {
            return userRepo.getById(id).get();
        }
    }
    @Cacheable(value="users")
    @Override
    public List<User> getAll() {
        return userRepo.getAll();
    }

    @CacheEvict(value="users")
    @Override
    public void delete(long id) {
         userRepo.deleteById(id);
    }
}
