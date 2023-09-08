package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Card;

import java.math.BigInteger;

public interface CardService extends DefaultService<Card> {

    String checkTimeToCardExpiration(Long cardId);

    //NEW
    void depositMoney(Long cardId, Double amount);
    void charge(Long cardId, Double amount);

}
