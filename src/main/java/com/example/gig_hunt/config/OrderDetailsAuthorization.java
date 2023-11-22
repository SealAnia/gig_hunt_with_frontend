package com.example.gig_hunt.config;

import com.example.gig_hunt.model.entity.Customer;
import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.entity.OrderDetails;
import com.example.gig_hunt.model.entity.User;
import com.example.gig_hunt.service.impl.OrderDetailsServiceImpl;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

public class OrderDetailsAuthorization {

    private final UserServiceImpl userService;
    private final OrderDetailsServiceImpl orderDetailsService;

    @Autowired
    public OrderDetailsAuthorization(UserServiceImpl userService, OrderDetailsServiceImpl orderDetailsService) {
        this.userService = userService;
        this.orderDetailsService = orderDetailsService;
    }

    public boolean check(Authentication authentication, Long orderId) {
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        OrderDetails order = orderDetailsService.readById(orderId);

        if (order == null) {
            return false;
        }

        else if (order.getCustomer() != null && order.getCustomer().getUserId() == user.getUserId()) {
            return true;
        }

        return false;
    }

    public boolean checkMaster(Authentication authentication, Long userId) {
        String username = authentication.getName();
        Master master = (Master) userService.loadUserByUsername(username);
        if(master.getUsername().equals(username) && master.getRole().getName().equals("ROLE_MASTER")
        && master.getUserId().equals(userId)) {
            return true;
        }
        return false;
    }

}
