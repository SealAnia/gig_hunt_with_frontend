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

    public boolean checkCustomer(Authentication authentication, Long userId) {
        String username = authentication.getName();
        System.out.println("USERNAME: " + username);

        User customer = (Customer) userService.loadUserByUsername(username);
        User u = (Customer) userService.readById(userId);

        System.out.println(customer.getUserId());

        if (customer.getRole().getName().equals("ROLE_CUSTOMER") && customer.getNickname().equals(username) &&
        customer.getUserId().equals(userId)) {
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
            return user.getRole().getName().equals("ROLE_MASTER") && master.getUserId() == userId;
        }
        return false;
    }

}