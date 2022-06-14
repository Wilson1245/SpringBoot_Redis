package com.example.javaTechieRedie.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@Builder
@RedisHash("Role")
public class Role {
    @Id
    private String id;

    @Indexed
    private String name;
}
