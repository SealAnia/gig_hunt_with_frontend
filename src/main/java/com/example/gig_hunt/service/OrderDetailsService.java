package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.OrderDetails;

import java.util.List;
import java.util.Set;

public interface OrderDetailsService extends DefaultService<OrderDetails> {

    Set<OrderDetails> getOrderForCustomer(Long userId);

    List<OrderDetails> getOrdersOfMaster(Long userId);
    List<OrderDetails> getOrdersOfMasterOrderByDate(Long userId);
    List<OrderDetails> getOrdersOfMasterOrderByStatus(Long userId);
    OrderDetails acceptOrder(OrderDetails orderDetails);
    OrderDetails declineOrder(OrderDetails orderDetails);
    OrderDetails setOrderCompleted(OrderDetails orderDetails);

}