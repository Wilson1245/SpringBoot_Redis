package com.example.BooksApi.repositoies;

import com.example.BooksApi.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Role findFirstByName(String name);
}
