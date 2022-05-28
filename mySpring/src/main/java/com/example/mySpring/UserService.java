package com.example.mySpring;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User FindById(Integer id);

}
