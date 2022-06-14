package com.example.javaTechieRedie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("User")
public class User {
    @Id
    private int id;
    private String name;
    private int age;

    @Indexed
    private String email;

    @Reference
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

//    @Reference
//    private Set<Product> products = new HashSet<>();
//
//    public void addProduct(Product product) {
//        products.add(product);
//    }
}
