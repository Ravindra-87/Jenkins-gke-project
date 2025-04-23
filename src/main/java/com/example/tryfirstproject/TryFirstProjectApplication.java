package com.example.tryfirstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.tryfirstproject.*")
@EnableJpaRepositories("com.example.tryfirstproject.*")
public class TryFirstProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TryFirstProjectApplication.class, args);
    }
}
