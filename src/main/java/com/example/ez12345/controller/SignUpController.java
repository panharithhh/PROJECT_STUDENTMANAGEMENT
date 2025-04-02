package com.example.ez12345.controller;

import org.springframework.core.env.Environment;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.sql.*;

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

        String sql = "INSERT INTO educators (full_name, email, password_hash) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

             PreparedStatement pstmt = con.prepareStatement(sql)) {
            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, encryptedPassword);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/login";
    }

}
