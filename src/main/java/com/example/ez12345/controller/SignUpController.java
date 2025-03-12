package com.example.ez12345.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.sql.*;

@Controller
public class SignUpController {

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @GetMapping("signup/login")
    public String login(){
        return "login";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam("full_name") String fullName,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                Model model) {
        try {
            if (createEducator(fullName, email, password)) {
                return "redirect:/authentication?email=" + email; // Redirect to authentication step
            } else {
                model.addAttribute("error", "Signup failed. Try again.");
                return "signup";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error.");
            return "signup";
        }
    }

    private boolean createEducator(String fullName, String email, String password) throws SQLException {
        String sql = "INSERT INTO educators (full_name, email, password_hash) VALUES (?, ?, ?)";
        String dbUrl = "jdbc:mysql://localhost:3306/test_schem";
        String dbUser = "root";
        String dbPassword = System.getenv("DB_PASSWORD");

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, encryptedPassword);
            stmt.executeUpdate();
            return true;
        }
    }
}