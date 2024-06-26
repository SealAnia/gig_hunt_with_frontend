package com.example.gig_hunt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/main_page")
public class MainPageController {

    @GetMapping(value = "")
    public String getLoginPage() {
        return "main_page";
    }

}
