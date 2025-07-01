package com.ipi.gestionchampionnat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}