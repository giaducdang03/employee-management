package com.ducdpg.employee_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenController {
    @GetMapping(path = "/login")
    public String login() {
        return "Hello world";
    }
}
