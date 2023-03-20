package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        Iterable<User> users = repo.findAll();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());
    }

    @Transactional
    public UserDTO getUser(int id) {
        Optional<User> user = repo.findById(id);
        if (user.isEmpty())
            return null;
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional
    public void addNewUser(UserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        repo.save(user);
    }

    @Transactional
    public void updateUser(UserDTO dto) {
        ensureUserExist(dto.getId());
        User newUser = modelMapper.map(dto, User.class);
        repo.save(newUser);
    }

    @Transactional
    public void deleteUser(int id) {
        repo.delete(ensureUserExist(id));
    }

    private User ensureUserExist(int id) {
        Optional<User> user = repo.findById(id);

        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return user.get();
    }
}
