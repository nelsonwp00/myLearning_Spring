package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(path = "/user")
    public UserDTO getUser(@RequestParam int id) {
        return service.getUser(id);
    }

    @PostMapping(path = "/add")
    public void addNewUser (@RequestBody UserDTO dto) {
        if (dto == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Parameter");

        service.addNewUser(dto);
    }

    @PutMapping(path = "/update")
    public void updateUser(@RequestBody UserDTO dto) {
        service.updateUser(dto);
    }

    @DeleteMapping(path = "/delete")
    public void removeUser(@RequestParam int id) {
        service.deleteUser(id);
    }
}