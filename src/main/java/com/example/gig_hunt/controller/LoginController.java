package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Admin;
import com.example.gig_hunt.model.entity.User;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/login_page")
public class LoginController {

    @GetMapping(value = "")
    public String getLoginPage() {
        return "login_page";
    }

    //private final UserServiceImpl service;

    //@Autowired
    //public LoginController(UserServiceImpl service) {
        //this.service = service;
    //}

    //@GetMapping(value = "")
    //public ResponseEntity<User> getLoginPage() {
        //User user = new User();
        //String username = user.getUsername();
        //user = (User) service.loadUserByUsername(username);
        //return ResponseEntity.ok().body(user);
    //}

}
