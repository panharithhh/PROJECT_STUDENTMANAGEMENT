package com.example.ez12345.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;

public class AttendanceController {

    private final Environment env;

    public AttendanceController(Environment env) {
        this.env = env;
    }




}
