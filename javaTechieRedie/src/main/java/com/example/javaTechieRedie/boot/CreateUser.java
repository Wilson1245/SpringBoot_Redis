package com.example.javaTechieRedie.boot;

import com.example.javaTechieRedie.entity.Role;
import com.example.javaTechieRedie.entity.User;
import com.example.javaTechieRedie.repository.RoleRepo;
import com.example.javaTechieRedie.repository.UserRepo;
import com.example.javaTechieRedie.service.ProductService;
import com.example.javaTechieRedie.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Order(3)
@Slf4j
public class CreateUser implements CommandLineRunner {

    private final UserService userService;

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    public CreateUser(UserService userService, RoleRepo RoleRepo, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.roleRepo = RoleRepo;
    }


    @Override
    public void run(String... args) throws Exception {
        if (userRepo.count() == 0) {
            Role admin = roleRepo.findFirstByName("admin");
            Role user = roleRepo.findFirstByName("user");

            try {
                ObjectMapper mapper = new ObjectMapper();

                InputStream inputStream = getClass().getResourceAsStream("/data/users/user.json");

                List<User> users = mapper.readValue(inputStream, new TypeReference<List<User>>(){});

                users.stream().forEach(u -> {
                    u.addRole(user);
                    userService.save(u);
                });

                log.info(">>>> Create User");
            } catch (IOException e) {
                log.info(">>>> Enable to create user");
            }

            User adminUser = new User();
            adminUser.setEmail("wilson@gmail.com");
            adminUser.setAge(11);
            adminUser.setId(1);
            adminUser.addRole(admin);

            log.info(">>>> Create Admin User");
        }
    }
}
