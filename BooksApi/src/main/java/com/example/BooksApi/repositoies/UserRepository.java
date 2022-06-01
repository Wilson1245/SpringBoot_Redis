package com.example.BooksApi.repositoies;

import com.example.BooksApi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findFirstByEmail(String email);
}
