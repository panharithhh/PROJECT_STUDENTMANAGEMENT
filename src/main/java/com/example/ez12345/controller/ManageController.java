package com.example.ez12345.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class ManageController {
    @GetMapping("temp/manage_student")
    public String showManageStudent() {
        return "manage_student";
    }

    @GetMapping("/create_courses")
    public String showCreateCourses() {
        return "create_courses";
    }

    @GetMapping("/forum")
    public String showForum() {
        return "forum";
    }

//    @GetMapping("/attendance")
//    public String showAttendance() {
//        return "attendance";
//    }



    @GetMapping("/progress")
    public String showProgress() {
        return "progress";
    }

    @GetMapping("/addAttendance")
    public String showAddAttendanceForm() {
        return "add_attendance"; // e.g., an HTML template with the form
    }

//    @Controller
//    public class attendanceController {
//        private final Environment env;
//
//        public attendanceController(Environment env) {
//            this.env = env;
//        }
//
//        @PostMapping("/addAttendance")
//        public String addAttendance(
//                @RequestParam("attendanceDays") int attendanceDays,
//                @RequestParam("studentID") int studentID
//        ) {
//            String dbUrl = env.getProperty("spring.datasource.url");
//            String dbUser = env.getProperty("spring.datasource.username");
//            String dbPassword = env.getProperty("spring.datasource.password");
//
//            String sql = "UPDATE students SET attendance = ? WHERE student_id = ?";
//            try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//                 PreparedStatement pstmt = con.prepareStatement(sql)) {
//
//                pstmt.setInt(1, attendanceDays);
//                pstmt.setInt(2, studentID);
//
//                pstmt.executeUpdate();
//
//            } catch (SQLException e) {
//
//                e.printStackTrace();
//            }
//            return "redirect:/attendance";
//        }
//    }

}

