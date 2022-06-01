package com.example.BooksApi.boot;

import com.example.BooksApi.models.Role;
import com.example.BooksApi.repositoies.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class CreateRole implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public CreateRole(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role adminRole = Role.builder().name("admin").build();
            Role customerRole = Role.builder().name("customer").build();
            roleRepository.save(adminRole);
            roleRepository.save(customerRole);
            log.info(">>>> Created admin and customer roles");
        }
    }
}
