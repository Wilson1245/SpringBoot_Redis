package com.example.javaTechieRedie.controllers;

import com.example.javaTechieRedie.entity.User;
import com.example.javaTechieRedie.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/all")
    public Iterator<User> findAll() {
        return userService.findAll();
    }

}
