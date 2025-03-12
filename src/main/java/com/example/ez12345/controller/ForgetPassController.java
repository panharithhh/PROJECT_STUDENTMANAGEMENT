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
@RequestMapping("/forgetpass") // Route for both GET and POST
public class ForgetPassController {

    private final Environment env;

    public ForgetPassController(Environment env) {
        this.env = env;
    }

    @GetMapping
    public String showForgetPassPage() {
        return "forgetpass"; //
    }

    @PostMapping
    public String sendPassword(@RequestParam("email") String email, Model model) {
        String sql = "SELECT password_hash FROM educators WHERE email = ?";

        try (Connection con = DriverManager.getConnection(
                env.getProperty("spring.datasource.url"),
                env.getProperty("spring.datasource.username"),
                env.getProperty("spring.datasource.password"));
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password_hash");


                    sendEmail(email, storedPassword);
                    model.addAttribute("message", "Password sent to your email.");
                } else {
                    model.addAttribute("error", "No user found with this email.");
                }
            }
        } catch (SQLException e) {
            model.addAttribute("error", "Database error, please try again.");
            e.printStackTrace();
        }

        return "forgetpass";
    }

    private void sendEmail(String toEmail, String password) {
        String senderEmail = "burberrith609@gmail.com";
        String senderPassword = env.getProperty("EMAIL_APP_PASSWORD");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your Password Recovery");
            message.setText("Your password is: " + password);
            Transport.send(message);
            System.out.println("✅ Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}