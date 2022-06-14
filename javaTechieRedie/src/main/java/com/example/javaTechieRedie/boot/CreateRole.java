package com.example.javaTechieRedie.boot;

import com.example.javaTechieRedie.entity.Role;
import com.example.javaTechieRedie.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class CreateRole implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepo.count() == 0) {
            Role admin = Role.builder().name("admin").build();
            Role user = Role.builder().name("user").build();
            roleRepo.save(admin);
            roleRepo.save(user);
            log.info(">>>> Create Role");
        }
    }
}
