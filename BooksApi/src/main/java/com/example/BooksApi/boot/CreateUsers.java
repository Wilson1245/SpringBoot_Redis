package com.example.BooksApi.boot;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.BooksApi.models.Role;
import com.example.BooksApi.models.User;
import com.example.BooksApi.repositoies.RoleRepository;
import com.example.BooksApi.repositoies.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;


@Component
@Order(2)
@Slf4j
public class CreateUsers implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public CreateUsers(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // load the roles
            Role admin = roleRepository.findFirstByName("admin");
            Role customer = roleRepository.findFirstByName("customer");

            try {
                // create a Jackson object mapper
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

                // create a type definition to convert the array of JSON into a List of Users
//                TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
//                };

                // make the JSON data available as an input stream
                InputStream inputStream = getClass().getResourceAsStream("/data/users/users.json");

                // convert the JSON to objects
                List<User> users = mapper.readValue(inputStream, new TypeReference<>(){});
//                User users = mapper.readValue(inputStream, new TypeReference<>(){});
//                log.info(">>>> users:", users);

                users.stream().forEach((user) -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.addRole(customer);
                    userRepository.save(user);
                });
                log.info(">>>> " + users.size() + " Users Saved!");
//                users.setPassword(passwordEncoder.encode(users.getPassword()));
//                users.addRole(customer);
//                userRepository.save(users);
//                log.info(">>>> " + users.toString() + " Users Saved!");
            } catch (IOException e) {
                log.info(">>>> Unable to import users: " + e.getMessage());
            }

            User adminUser = new User();
            adminUser.setName("Adminus Admistradore");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("Reindeer Flotilla"));//
            adminUser.addRole(admin);

            userRepository.save(adminUser);
            log.info(">>>> Loaded User Data and Created users...");
        }
    }
}
