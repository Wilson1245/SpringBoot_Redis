package com.example.RedisHash;

import com.example.RedisHash.models.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j // 創建 .log 檔案
public class CreateRoles implements CommandLineRunner {

    private final RoleRepository repository;

    public CreateRoles(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            Role adminRole = Role.builder().name("admin").build();
            Role customerRole = Role.builder().name("customer").build();
            repository.save(adminRole);
            repository.save(customerRole);
            log.info(">>>> Created admin and customer roles...");
        }
    }
}
