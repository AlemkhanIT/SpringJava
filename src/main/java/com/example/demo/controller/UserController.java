package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class UserController {

    public static final List<User> users = new ArrayList<>();

    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @GetMapping
    public List<User> listUsers(@RequestParam(name = "lastname", required = false) String lastName) {
        if (lastName != null) {
            return users.stream()
                    .filter(u -> u.getLastName().equalsIgnoreCase(lastName))
                    .collect(Collectors.toList());
        }
        return users;
    }

    @GetMapping("/{userId}")
    public User readUser(@PathVariable Long userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User existingUser = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setContact(updatedUser.getContact());
        }

        return existingUser;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        users.removeIf(u -> u.getId().equals(userId));
    }
}
