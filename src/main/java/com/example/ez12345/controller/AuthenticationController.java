package com.example.ez12345.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@Controller
public class AuthenticationController {

    @GetMapping("/auth")
    public String showAuthenticationPage(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "authentication"; // Redirects to authentication.html
    }

    @PostMapping("/verifyAuthentication")
    public String verifyAuthentication(@RequestParam("email") String email,
                                       @RequestParam("authCode") String userCode,
                                       Model model) {
        String storedCode = getStoredCode(email);

        if (storedCode != null && storedCode.equals(userCode)) {
            moveUserToMainTable(email);
            return "redirect:/login"; // Redirect to login page after successful authentication
        } else {
            model.addAttribute("error", "Invalid authentication code.");
            return "authentication";
        }
    }

    private String getStoredCode(String email) {
        String sql = "SELECT auth_code FROM temp_users WHERE email = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schem", "root", System.getenv("DB_PASSWORD"));
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("auth_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void moveUserToMainTable(String email) {
        String moveSql = "INSERT INTO educators (full_name, email, password_hash) SELECT full_name, email, password_hash FROM temp_users WHERE email = ?";
        String deleteSql = "DELETE FROM temp_users WHERE email = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schem", "root", System.getenv("DB_PASSWORD"));
             PreparedStatement moveStmt = con.prepareStatement(moveSql);
             PreparedStatement deleteStmt = con.prepareStatement(deleteSql)) {

            moveStmt.setString(1, email);
            deleteStmt.setString(1, email);

            moveStmt.executeUpdate();
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
