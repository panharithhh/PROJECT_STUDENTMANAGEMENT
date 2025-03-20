package com.example.ez12345.controller;


import org.springframework.ui.Model;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ScoreController {

    private final Environment env; // Interface and object

    public ScoreController(Environment env) {
        this.env = env;
    }

    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        List<ManageStudentController.Student> students = fetchScore();
        model.addAttribute("students", students);
        return "quiz";
    }

    @PostMapping("/quiz")
    public String addStudent(
            @RequestParam("studentName") String fullName,
            @RequestParam("") String email,
            @RequestParam("studentCourse") String course,
            @RequestParam("studentAcademicYear") String academicYear) {

        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUser = env.getProperty("spring.datasource.username");
        String dbPassword = env.getProperty("spring.datasource.password");

        String sql = "INSERT INTO students (full_name, email, course, academic_year) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, course);
            pstmt.setString(4, academicYear);

            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/manage_student";
    }
    private List<ManageStudentController.Student> fetchScore() {
        List<ManageStudentController.Student> studentScore = new ArrayList<>();


        return studentScore;
    }

}
