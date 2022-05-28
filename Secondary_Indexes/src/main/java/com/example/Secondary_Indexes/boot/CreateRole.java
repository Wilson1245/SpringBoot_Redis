package com.example.Secondary_Indexes.boot;

import com.example.Secondary_Indexes.RoleRepository;
import com.example.Secondary_Indexes.models.Role;
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
