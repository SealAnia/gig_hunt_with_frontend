package com.example.gig_hunt.config;

import com.example.gig_hunt.model.entity.User;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

public class CardAuthorization {

    private final UserServiceImpl userService;

    @Autowired
    public CardAuthorization(UserServiceImpl userService) {
        this.userService = userService;
    }

    public boolean check(Authentication authentication, Long cardId) {
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        System.out.println("USERNAME " + username);
        if (user != null && user.getNickname().equals(username) && user.getCard() != null &&
                user.getCard().getCardId() == cardId) {
            return user.getCard().getCardId() != null && user.getCard().getCardId() == cardId;
        }

        return false;
    }

}

