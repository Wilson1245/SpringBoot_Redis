package com.example.RedisHash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RedisHashApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisHashApplication.class, args);
	}

}
