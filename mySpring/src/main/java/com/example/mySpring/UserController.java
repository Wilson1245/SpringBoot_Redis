package com.example.mySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/all")
    public List<User> getUser() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userServiceImpl.FindById(id);
    }
}
