package com.monozel.questAppbackend.services;

import com.monozel.questAppbackend.entities.User;
import com.monozel.questAppbackend.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User foundedUser = user.get();
            foundedUser.setUserName(newUser.getUserName());
            foundedUser.setPassword(newUser.getPassword());
            userRepository.save(foundedUser);
            return foundedUser;
        }else {
            // custom exception
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
