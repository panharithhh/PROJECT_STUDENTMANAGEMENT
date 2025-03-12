package com.example.ez12345.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/attendance")
    public String showAttendance() {
        return "attendance";
    }

    @GetMapping("/quiz")
    public String showQuiz() {
        return "quiz";
    }

    @GetMapping("/progress")
    public String showProgress() {
        return "progress";
    }
}