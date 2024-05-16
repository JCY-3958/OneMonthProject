package com.codehows.mothproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MothProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MothProjectApplication.class, args);
    }

}
