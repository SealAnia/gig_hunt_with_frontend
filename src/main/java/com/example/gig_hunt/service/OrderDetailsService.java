package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsService extends DefaultService<OrderDetails> {

    List<OrderDetails> getOrdersOfMaster(Long userId);
    OrderDetails acceptOrder(OrderDetails orderDetails);
    OrderDetails declineOrder(OrderDetails orderDetails);
    OrderDetails setOrderCompleted(OrderDetails orderDetails);

}