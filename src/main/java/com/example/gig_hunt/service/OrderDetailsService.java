package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsService extends DefaultService<OrderDetails> {

    List<OrderDetails> getOrdersOfMaster(Long userId);
    public OrderDetails acceptOrder(OrderDetails orderDetails);
    public OrderDetails declineOrder(OrderDetails orderDetails);
    public OrderDetails setOrderCompleted(OrderDetails orderDetails);

}