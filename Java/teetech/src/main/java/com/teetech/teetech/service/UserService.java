package com.teetech.teetech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teetech.teetech.User;
import com.teetech.teetech.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
            .map(user -> {
                user.setName(userDetails.getName());
                user.setEmail(userDetails.getEmail());
                user.setGender(userDetails.getGender());
                return userRepository.save(user);
            })
            .orElse(null);
    }

    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
            .map(user -> {
                userRepository.delete(user);
                return true;
            })
            .orElse(false);
    }
}
