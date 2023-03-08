package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public Iterable<User> getAllUsers() {
        return repo.findAll();
    }

    public void addNewUser(String name, String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        repo.save(n);
    }
}
