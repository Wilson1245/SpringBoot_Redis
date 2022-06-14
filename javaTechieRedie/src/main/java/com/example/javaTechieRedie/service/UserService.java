package com.example.javaTechieRedie.service;

import com.example.javaTechieRedie.entity.User;
import com.example.javaTechieRedie.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void saveAll(List<User> users) {
        userRepo.saveAll(users);
    }

    public Optional<User> findById(int id) {
        return userRepo.findById(id);
    }

    public User findByEmail(String email) {
        return userRepo.findFirstByEmail(email);
    }

    public User update(User user) {
        return userRepo.save(user);
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }

    public Iterator<User> findAll() {
        return userRepo.findAll().iterator();
    }
}
