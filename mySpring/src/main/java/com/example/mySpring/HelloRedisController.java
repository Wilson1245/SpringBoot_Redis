package com.example.mySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.AbstractMap.SimpleEntry;

import java.util.Map;

@RestController
@RequestMapping("/api/redis")
public class HelloRedisController {

    @Autowired
    private RedisTemplate<String, String> template;

    private static final String STRING_KEY_PREFIX = "redis2read:strings:";

    @PostMapping("/strings")
    @ResponseStatus(HttpStatus.CREATED)
    public Map.Entry<String, String> setString(@RequestBody Map.Entry<String, String> kvp) {
        template.opsForValue().set(STRING_KEY_PREFIX + kvp.getKey(), kvp.getValue());
        return kvp;
    }

    @GetMapping("/strings/{key}")
    public Map.Entry<String, String> getString(@PathVariable String key) {
        String value = template.opsForValue().get(STRING_KEY_PREFIX + key);

        if (value == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "key not found");
        }

        return new SimpleEntry<String, String>(key, value);
    }
}
