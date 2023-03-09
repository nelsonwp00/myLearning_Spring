package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    public Iterable<User> getAllUsers() {
        return repo.findAll();
    }

    public void addNewUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        repo.save(user);
    }

    public void updateUser(UserDTO dto) {
        getUserOrException(dto.getId());
        User newUser = modelMapper.map(dto, User.class);
        repo.save(newUser);
    }

    public void deleteUser(int id) {
        repo.delete(getUserOrException(id));
    }

    private User getUserOrException(int id) {
        Optional<User> user = repo.findById(id);

        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return user.get();
    }
}
