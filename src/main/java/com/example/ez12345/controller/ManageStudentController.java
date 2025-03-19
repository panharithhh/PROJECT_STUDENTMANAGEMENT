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

@Controller
public class ManageStudentController {


    private final Environment env; // Interface and object

    public ManageStudentController(Environment env) {
        this.env = env; //
    }

    @GetMapping("/manage_student")
    public String getStudents(Model model) {
        List<Student> students = fetchStudents();
        model.addAttribute("students", students);
        return "manage_student";
    }

    @PostMapping("/add_student")
    public String addStudent(
            @RequestParam("studentName") String fullName,
            @RequestParam("studentEmail") String email,
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

    @PostMapping("/delete_student")
    public String deleteStudent(@RequestParam("studentId") int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUser = env.getProperty("spring.datasource.username");
        String dbPassword = env.getProperty("spring.datasource.password");

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student successfully deleted.");
            } else {
                System.out.println("No student found with ID: " + studentId);
            }

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/manage_student";
    }

    private List<Student> fetchStudents() {
        List<Student> students = new ArrayList<>();
        String dbUrl = "jdbc:mysql://localhost:3306/test_schem";
        String dbUser = "root";
        String dbPassword = System.getenv("DB_PASSWORD");
        String sql = "SELECT student_id, full_name, email, course, academic_year FROM students";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getString("academic_year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public class Student {
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
