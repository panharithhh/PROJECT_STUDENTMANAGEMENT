
package com.example.ez12345.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class ProfileController {

    private final Environment env;

    public ProfileController(Environment env) {
        this.env = env;
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable("id") int studentId, Model model) {
        Student student = fetchStudentById(studentId);
        if (student == null) {
            return "redirect:/manage_student";
        }
        model.addAttribute("student", student);
        return "profile";
    }

    private Student fetchStudentById(int studentId) {
        String dbUrl = env.getProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/test_schem");
        String dbUser = env.getProperty("spring.datasource.username", "root");
        String dbPassword = env.getProperty("spring.datasource.password");
        String sql = "SELECT student_id, full_name, email, course, academic_year FROM students WHERE student_id = ?";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("course"),
                            rs.getString("academic_year")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static class Student {
        private final int studentId;
        private final String fullName;
        private final String email;
        private final String course;
        private final String academicYear;

        public Student(int studentId, String fullName, String email, String course, String academicYear) {
            this.studentId = studentId;
            this.fullName = fullName;
            this.email = email;
            this.course = course;
            this.academicYear = academicYear;
        }

        public int getStudentId() {
            return studentId;
        }

        public String getFullName() {
            return fullName;
        }

        public String getEmail() {
            return email;
        }

        public String getCourse() {
            return course;
        }

        public String getAcademicYear() {
            return academicYear;
        }
    }
}
