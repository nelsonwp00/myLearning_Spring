package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "user")
public class UserController {
    @Autowired // This means to get the bean called userRepository, which is auto-generated by Spring
    private UserService service;

    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping(path = "/add")
    public void addNewUser (@RequestParam String name, @RequestParam String email) {
        if (name.isEmpty() || email.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Parameter");

        service.addNewUser(name, email);
    }

    @PutMapping(path = "/update")
    public void updateUser(@RequestBody UserDTO dto) {
        service.updateUser(dto);
    }

    @DeleteMapping(path = "/delete")
    public void deleteUser(@RequestParam int id) {
        service.deleteUser(id);
    }
}