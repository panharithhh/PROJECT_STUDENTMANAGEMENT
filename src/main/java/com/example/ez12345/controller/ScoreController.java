package com.example.ez12345.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class ScoreController {

    private final Environment env;

    public ScoreController(Environment env) {
        this.env = env;
    }

    @GetMapping("/quiz")
    public String showQuizPage(Model model) {
        List<Map<String, Object>> scores = fetchStudentScores();
        model.addAttribute("scores", scores);
        return "quiz";
    }

    @PostMapping("/add-scores")
    public String addScores(
            @RequestParam("studentId") int studentId,
            @RequestParam("homework") double homework,
            @RequestParam("midterm") double midterm,
            @RequestParam("final_score") double finalExam,
            @RequestParam("project") double project) {

        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUser = env.getProperty("spring.datasource.username");
        String dbPassword = env.getProperty("spring.datasource.password");

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "UPDATE students SET homework = ?, midterm = ?, final_exam = ?, quiz = ? WHERE student_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDouble(1, homework);
                stmt.setDouble(2, midterm);
                stmt.setDouble(3, finalExam);
                stmt.setDouble(4, project);
                stmt.setInt(5, studentId);

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/quiz";
    }

    private List<Map<String, Object>> fetchStudentScores() {
        List<Map<String, Object>> students = new ArrayList<>();
        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUser = env.getProperty("spring.datasource.username");
        String dbPassword = env.getProperty("spring.datasource.password");

        String sql = "SELECT student_id, full_name, homework, midterm, final_exam, quiz FROM students";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> student = new HashMap<>();

                student.put("student_id", rs.getInt("student_id"));
                student.put("full_name", rs.getString("full_name"));
                student.put("homework", rs.getDouble("homework"));
                student.put("midterm", rs.getDouble("midterm"));
                student.put("final_score", rs.getDouble("final_exam"));
                student.put("project", rs.getDouble("quiz"));

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}

