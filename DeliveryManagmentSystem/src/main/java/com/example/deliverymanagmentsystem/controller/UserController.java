package com.example.deliverymanagmentsystem.controller;

import com.example.deliverymanagmentsystem.config.LocaleResolverConfig;
import com.example.deliverymanagmentsystem.config.MessagingCinfig;
import com.example.deliverymanagmentsystem.controller.errors.ResourceNotFoundException;
import com.example.deliverymanagmentsystem.model.user.User;
import com.example.deliverymanagmentsystem.service.userservice.UserServiceImpl;
import jakarta.validation.Valid;
import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;

//import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @PostMapping
    public ResponseEntity<User> add(@RequestHeader(name="Accept-Language") @Valid @RequestBody User user ) {
        return ResponseEntity.accepted().body(userService.add(user));
    }
    @PostMapping("/rabbit")
    public String rabbit(@RequestBody User user){
        user.setId(1);
        rabbitTemplate.convertAndSend(MessagingCinfig.EXCHANGE,MessagingCinfig.ROUTING_KEY,user);
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
//        if(user==null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
