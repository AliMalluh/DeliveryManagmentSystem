package com.example.deliverymanagmentsystem;

import com.example.deliverymanagmentsystem.model.user.Roles;
import com.example.deliverymanagmentsystem.model.user.User;
import com.example.deliverymanagmentsystem.service.userservice.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class UserMVCTest {
    private static final long USER_ID = 1;
    private static final String USER_FIRST_NAME_STRING = "Ali";
    private static final String USER_LAST_NAME_STRING = "Malluh";
    private static final Roles USER_ROLE= Roles.EMPLOYEE;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void testFindAll() throws Exception {
        User user = buildUser();
        List<User> users = Arrays.asList(user);
        when(userService.getAll()).thenReturn(users);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get("http://localhost:8080/user/users").contextPath("")).andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(users)));
    }

    @Test
    public void testFindById() throws Exception {
        User user = buildUser();
        when(userService.get(USER_ID)).thenReturn(Optional.of(user));
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get("http://localhost:8080/user/"+USER_ID).contextPath("")).andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(user)));
    }

    @Test
    public void testCreatUser() throws Exception {
        User user = buildUser();
        when(userService.add(user)).thenReturn(user);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post("http://localhost:8080/user").contextPath("").contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(user))).andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(user)));
    }

    @Test
    public void testDeleteUser() throws Exception {
//        when(userService.delete(USER_ID));
        mockMvc.perform(delete("http://localhost:8080/user/"+USER_ID).contextPath("")).andExpect(status().isOk());
    }
    private User buildUser() {
        User user = new User();
        user.setId(USER_ID);
        user.setFirstname(USER_FIRST_NAME_STRING);
        user.setLastname(USER_LAST_NAME_STRING);
        user.setRole(USER_ROLE);
        return user;
    }
}
