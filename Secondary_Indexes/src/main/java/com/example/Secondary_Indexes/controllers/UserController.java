package com.example.Secondary_Indexes.controllers;

import com.example.Secondary_Indexes.UserRepository;
import com.example.Secondary_Indexes.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Path: /api/users/?email=[] || /api/users/
    @GetMapping
    public Iterable<User> all(@RequestParam(defaultValue = "") String email) {
        if (email.isEmpty()) {
            return userRepository.findAll();
        } else {
            Optional<User> user = Optional.ofNullable(userRepository.findFirstByEmail(email));
            return user.isPresent() ? List.of(user.get()) : Collections.emptyList();
        }
    }
}
