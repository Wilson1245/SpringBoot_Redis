package com.example.mySpring;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "userService")
public class UserServiceImpl implements UserService{

    private final UserRepository userDao;

    public UserServiceImpl(UserRepository userRepository) {
        this.userDao = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User FindById(Integer id) {
        Optional<User> _User = userDao.findById(id);
        return Optional.ofNullable(_User).get().orElse(null);
    }

}
