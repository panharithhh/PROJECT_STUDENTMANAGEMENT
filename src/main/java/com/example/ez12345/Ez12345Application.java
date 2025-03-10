package com.example.ez12345;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties") // Ensures application.properties is loaded
public class Ez12345Application {
    public static void main(String[] args) {
        SpringApplication.run(Ez12345Application.class, args);
    }
}



