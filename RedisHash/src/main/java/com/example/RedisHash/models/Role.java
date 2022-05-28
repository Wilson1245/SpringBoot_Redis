package com.example.RedisHash.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash
@Data
@Builder
public class Role {
    @Id
    private String id;
    private String name;
}
