package com.example.gig_hunt.config;

import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.entity.User;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

public class CompanyAuthorization {

    private final UserServiceImpl userService;

    @Autowired
    public CompanyAuthorization(UserServiceImpl userService) {
        this.userService = userService;
    }

    public boolean check(Authentication authentication, Long companyId) {
        String username = authentication.getName();
        Master user = (Master) userService.loadUserByUsername(username);
        User u = (Master) userService.readById(user.getUserId());
        if(user != null && user.getNickname().equals(username)
                && user.getRole().getName().equals("ROLE_MASTER") && user.getCompany() != null) {
            return user.getCompany().getCompanyId() != null && user.getCompany().getCompanyId() == companyId;
        }
        return false;
    }

}
