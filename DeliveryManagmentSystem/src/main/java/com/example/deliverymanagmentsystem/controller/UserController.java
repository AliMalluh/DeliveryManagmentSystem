package com.example.deliverymanagmentsystem.controller;

import com.example.deliverymanagmentsystem.config.MessagingConfig;
import com.example.deliverymanagmentsystem.controller.errors.ResourceNotFoundException;
import com.example.deliverymanagmentsystem.model.user.AuthRequest;
import com.example.deliverymanagmentsystem.model.user.User;
import com.example.deliverymanagmentsystem.service.JwtService;
import com.example.deliverymanagmentsystem.service.userservice.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestHeader(name="Accept-Language") @Valid @RequestBody User user ) {
        return ResponseEntity.accepted().body(userService.add(user));
    }
    @PostMapping("/rabbit")
    public String rabbit(@RequestBody User user){
        user.setId(1);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,user);
        return "success";
    }
    @GetMapping(value = "/test/with-header", produces = "text/plain; charset=UTF-8")
    public String test(@RequestHeader(name="Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("common.hello",null, LocaleContextHolder.getLocale());
    }
    @PutMapping
    public ResponseEntity update(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity(user,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id) throws ResourceNotFoundException {
        User user = userService.get(id);
        if(user==null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAll() {

        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {

            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    @GetMapping("/getUser")
    public ResponseEntity<User>  getUserFromToken(){
        User user = jwtService.getUserFromToken();
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
