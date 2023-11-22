package com.example.gig_hunt.config;

import com.example.gig_hunt.model.entity.Customer;
import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.entity.User;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

public class UserAuthorization {

    private final UserServiceImpl userService;

    @Autowired
    public UserAuthorization(UserServiceImpl userService) {
        this.userService = userService;
    }

    public boolean check(Authentication authentication, Long userId) {
        String username = authentication.getName();
        System.out.println(username);

        Customer customer = (Customer) userService.loadUserByUsername(username);
        User u = userService.readById(userId);

        System.out.println(customer.getUserId());
        System.out.println(u.getUserId());

        if (userId.equals(customer.getUserId())) {
            return true;
        }

        return false;
    }

    public boolean checkMaster(Authentication authentication, Long userId) {
        String username = authentication.getName();
        System.out.println(username);
        Master master = (Master) userService.loadUserByUsername(username);
        User user = userService.readById(userId);
        System.out.println(master.getUserId());
        System.out.println(userId);
        if(user.getRole().getName().equals("ROLE_MASTER") && master.getUserId().equals(userId)) {
            return user.getRole().getName().equals("ROLE_MASTER") && master.getUserId().equals(userId);
        }
        return false;
    }

}