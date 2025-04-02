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
public class attendanceController {
    private final Environment env;




    @GetMapping("/attendance")
    public String showAttendance(Model model) {
        List<attendanceController.StudentAttendance> students = fetchStudent();
        model.addAttribute("students", students);
        return "attendance"
                ;
    }
    public attendanceController(Environment env) {
        this.env = env;
    }

    @PostMapping("/addAttendance")
    public String addAttendance (
            @RequestParam("attendanceDays") String attendanceDays ,
            @RequestParam("studentId") int studentID

    ){
        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUser = env.getProperty("spring.datasource.username");
        String dbPassword = env.getProperty("spring.datasource.password");

        String sql = "UPDATE students SET attendance = ? WHERE student_id = ?";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, attendanceDays);
            pstmt.setInt(2, studentID);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return "redirect:/attendance";
    }

    private List<StudentAttendance> fetchStudent() {
        List<StudentAttendance> students = new ArrayList<>();
        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUser = env.getProperty("spring.datasource.username");
        String dbPassword = env.getProperty("spring.datasource.password");
        String sql = "SELECT student_id, full_name, attendance FROM students";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new StudentAttendance(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("attendance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    public class StudentAttendance {
        private int studentId;
        private String fullName;
        private String attendance;


        public StudentAttendance(int studentId, String fullName, String attendance) {
            this.studentId = studentId;
            this.fullName = fullName;
            this.attendance = attendance;

        }


        public String getAttendance() {
            return attendance;
        }

        public String getFullName() {
            return fullName;
        }

        public int getStudentId() {
            return studentId;
        }


    }

}

