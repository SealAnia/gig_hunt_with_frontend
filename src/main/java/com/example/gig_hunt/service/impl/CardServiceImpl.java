package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.exception.NumberOfSymbolsDifferentFromRequiredException;
import com.example.gig_hunt.model.entity.Card;
import com.example.gig_hunt.model.repository.CardRepository;
import com.example.gig_hunt.service.CardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card readById(Long id) {
        return cardRepository.findById(id).get();
    }

    @Override
    public Card createOrUpdate(Card card) throws NumberOfSymbolsDifferentFromRequiredException {
        int i = 3;
        if(String.valueOf(card.getCvv()).length() != 3) {
            throw new NumberOfSymbolsDifferentFromRequiredException(3);
        }
        return cardRepository.saveAndFlush(card);
    }

    @Override
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public String checkTimeToCardExpiration(Long cardId) {
        return cardRepository.checkTimeToCardExpiration(cardId);
    }

    @Override
    @Transactional
    public void depositMoney(Long cardId, Double amount) {
        Card card = cardRepository.findById(cardId).get();
        card.setTotalAmount(card.getTotalAmount() + amount);
    }

    @Override
    @Transactional
    public void charge(Long cardId, Double amount) {
        Card card = cardRepository.findById(cardId).get();
        card.setTotalAmount(card.getTotalAmount() - amount);
    }

}