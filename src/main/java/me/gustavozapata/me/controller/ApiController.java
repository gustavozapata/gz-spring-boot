package me.gustavozapata.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {

    @GetMapping("/api")
    public String api(){
        return "api.html";
    }
}
