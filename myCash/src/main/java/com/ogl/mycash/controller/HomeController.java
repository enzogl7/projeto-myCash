package com.ogl.mycash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "/home/home_primeiro_login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/home/home";
    }
}
