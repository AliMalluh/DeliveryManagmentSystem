package com.example.deliverymanagmentsystem;

import com.example.deliverymanagmentsystem.model.user.Roles;
import com.example.deliverymanagmentsystem.model.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DeliveryManagmentSystemApplicationTests {

    @Test
    void testGetUser() {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject("http://localhost:8080/user/" + "1", User.class);
        assertNotNull(user);
        assertEquals("Ali", user.getFirstname());
    }

    @Test
    public void testCreateUser() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setId(4);
        user.setFirstname("ssss");
        user.setLastname("ssss");
        user.setRole(Roles.EMPLOYEE);
        User newUser = restTemplate.postForObject("http://localhost:8080/user", user, User.class);

        assertNotNull(newUser);
        assertNotNull(newUser.getId());

        assertEquals("ssss", newUser.getFirstname());
    }

    @Test
    public void testUpdateUser(){
        RestTemplate restTemplate = new RestTemplate();

    }

}
