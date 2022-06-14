package com.example.javaTechieRedie.repository;

import com.example.javaTechieRedie.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findFirstByEmail(String email);
}
