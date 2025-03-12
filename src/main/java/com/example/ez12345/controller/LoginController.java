package com.example.ez12345.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.sql.*;
import java.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;

@Controller // act as the center point connecting web with backend
public class LoginController {

    private final Environment env; // Interface and object

    public LoginController(Environment env) {
        this.env = env; //
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        return verifyCredentials(email, password) ? "redirect:/manage_student" : setError(model);
    }

    @GetMapping("/manage_student")
    public String showManageStudentPage() {
        return "manage_student";
    }

    private boolean verifyCredentials(String email, String plainPassword) {
        String sql = "SELECT password_hash FROM educators WHERE email = ?";
        try (Connection con = DriverManager.getConnection(

                env.getProperty("spring.datasource.url"),
                env.getProperty("spring.datasource.username"),
                env.getProperty("spring.datasource.password"));

             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && BCrypt.checkpw(plainPassword, rs.getString("password_hash"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String setError(Model model) {
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }
}