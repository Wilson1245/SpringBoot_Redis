package com.example.javaTechieRedie.repository;

import com.example.javaTechieRedie.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, String> {
    Role findFirstByName(String name);
}
