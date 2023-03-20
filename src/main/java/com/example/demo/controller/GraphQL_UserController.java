package com.example.demo.controller;


import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQL_UserController {
    private final UserService service;

    @Autowired
    public GraphQL_UserController(UserService service) {
        this.service = service;
    }

    @QueryMapping
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @QueryMapping
    public UserDTO getUser(@Argument int id) {
        return service.getUser(id);
    }

    @MutationMapping
    public boolean addUser(@Argument UserDTO user) {
        service.addNewUser(user);
        return true;
    }

    @MutationMapping
    public boolean updateUser(@Argument UserDTO user) {
        service.updateUser(user);
        return true;
    }

    @MutationMapping
    public boolean removeUser(@Argument int id) {
        service.deleteUser(id);
        return true;
    }
}
