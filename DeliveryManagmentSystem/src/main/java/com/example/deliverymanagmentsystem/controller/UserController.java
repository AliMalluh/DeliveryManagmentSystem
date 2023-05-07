package com.example.deliverymanagmentsystem.controller;

import com.example.deliverymanagmentsystem.model.user.User;
import com.example.deliverymanagmentsystem.service.userservice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity add(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity(user,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        Optional<User> user = userService.get(id);
        return new ResponseEntity(user.get(), HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
