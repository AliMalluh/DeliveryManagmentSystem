package com.example.deliverymanagmentsystem.service.userservice;

import com.example.deliverymanagmentsystem.controller.errors.ResourceNotFoundException;
import com.example.deliverymanagmentsystem.dao.userrepo.UserRepo;
import com.example.deliverymanagmentsystem.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Override
    public User add(User user) {
        if (!user.getRole().toString().equals("CLIENT")){
            user.setStoreId(null);
        }
        return userRepo.save(user);
    }

    public User update(User user) {
        if (!user.getRole().toString().equals("CLIENT")){
            user.setStoreId(null);
        }
        return userRepo.update(user);
    }

    public User get(long id) {
        if(userRepo.getById(id).isEmpty()){
            System.out.println(HttpStatus.NOT_FOUND.name());
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.name(),"No Employee");
        }else {
            return userRepo.getById(id).get();
        }
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    @Override
    public void delete(long id) {
         userRepo.deleteById(id);
    }
}
