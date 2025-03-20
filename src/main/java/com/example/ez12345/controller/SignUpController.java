package com.example.ez12345.controller;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.Properties;

@Controller
public class SignUpController {


    private final Environment env;

    public SignUpController(Environment env) {
        this.env = env;
    }


    @GetMapping("signup")
    public String getEducators() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUser = env.getProperty("spring.datasource.username");
        String dbPassword = env.getProperty("spring.datasource.password");

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Failed to register user.");
            }

        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
            e.printStackTrace();
        }


        return "redirect:/login";
    }

}