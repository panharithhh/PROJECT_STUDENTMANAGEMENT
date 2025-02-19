package com.example.ez12345;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@SpringBootApplication // allow for a Springboot appplication and removes the needs for alot of configuration manually
@RestController // this line bascially act as a joint part between the web and the backend
public class Ez12345Application {
    public static void main(String[] args) {
        SpringApplication.run(Ez12345Application.class, args); // starts the spring boot application
    }

    @PostMapping("/submitSignup") // handles http posts request tp /submitSignup
    public ResponseEntity<Void> receiveSignupData( // don't do anything just receive the response
             @RequestParam(value = "username") String username,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "confirmPassword") String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println("Received Signup Data: " + username + ", " + email);
 // Corrected redirection to authentication.html
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/login-signup/authentication.html")) // Use URI, not URl
                .build();
    }
}