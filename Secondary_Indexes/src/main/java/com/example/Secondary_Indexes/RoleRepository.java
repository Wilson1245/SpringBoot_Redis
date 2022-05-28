package com.example.Secondary_Indexes;

import com.example.Secondary_Indexes.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Role findFirstByName(String name);
}
