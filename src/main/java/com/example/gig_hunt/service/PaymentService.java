package com.example.gig_hunt.service;

public interface PaymentService {

    void pay(Long cardFirstId, Long cardSecondId, Long orderId);

}
