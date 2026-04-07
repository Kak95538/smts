package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    List<User> users = new ArrayList<>();

    // 6.1 + 6.2 GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // 6.2 Path Variable
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 6.2 Request Body + 6.3 ResponseEntity
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.status(201).body(user);
    }

    // Query @param example
    @GetMapping("/search")
    public List<User> search(@RequestParam String name) {
        return users.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .toList();
    }
}