package com.example.gig_hunt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessDeniedController {

    @GetMapping(value = "/access_denied")
    public String accessDenied() {
        return "ACCESS DENIED";
    }

}
