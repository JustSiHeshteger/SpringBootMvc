package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    void updateUser(User user);

    void removeById(Long id);
}
