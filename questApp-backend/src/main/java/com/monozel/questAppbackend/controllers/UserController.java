package com.monozel.questAppbackend.controllers;

import com.monozel.questAppbackend.entities.User;
import com.monozel.questAppbackend.repos.UserRepository;
import com.monozel.questAppbackend.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping  //  path == "/users"
    public List<User> getAllUsers () {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser (@RequestBody User newUser) {
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser (@PathVariable Long userId) {
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser (@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser (@PathVariable Long userId) {
        userService.deleteById(userId);
    }
}
