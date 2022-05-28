package com.example.RedisHash;

import com.example.RedisHash.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
}
