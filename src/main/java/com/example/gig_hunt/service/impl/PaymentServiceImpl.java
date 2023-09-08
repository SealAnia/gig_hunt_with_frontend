package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.OrderDetails;
import com.example.gig_hunt.model.repository.OrderDetailsRepository;
import com.example.gig_hunt.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final CardServiceImpl cardService;

    @Autowired
    public PaymentServiceImpl(OrderDetailsRepository orderDetailsRepository, CardServiceImpl cardService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.cardService = cardService;
    }

    @Transactional
    @Override
    public void pay(Long cardFirstId, Long cardSecondId, Long orderId) {
        OrderDetails order = orderDetailsRepository.findById(orderId).get();
        Double orderAmount = order.getCost();
        cardService.charge(cardFirstId, orderAmount);
        cardService.depositMoney(cardSecondId, orderAmount);
    }

}