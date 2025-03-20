package com.example.ez12345.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ManageController {
    @GetMapping("temp/manage_student")
    public String showManageStudent() {
        return "manage_student";
    }



    @GetMapping("/addAttendance")
    public String showAddAttendanceForm() {
        return "add_attendance"; // e.g., an HTML template with the form
    }

}

