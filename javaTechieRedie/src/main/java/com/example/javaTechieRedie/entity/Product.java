package com.example.javaTechieRedie.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("product")
public class Product implements Serializable {
    @Id
    private int id;

    @Indexed
    private String name;

    private int qty;

    private long price;
}
