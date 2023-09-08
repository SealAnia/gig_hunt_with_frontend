package com.example.gig_hunt.controller;

import com.example.gig_hunt.exception.NumberOfSymbolsDifferentFromRequiredException;
import com.example.gig_hunt.model.entity.Card;
import com.example.gig_hunt.service.impl.CardServiceImpl;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/cards")
public class CardRestController {

    private final CardServiceImpl cardService;
    private final UserServiceImpl userService;

    @Autowired
    public CardRestController(CardServiceImpl cardService, UserServiceImpl userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    @GetMapping(value = "")
    public List<Card> getAllCards() {
        return cardService.getAll();
    }

    @GetMapping(value = "/{cardId}")
    public Card getCardById(@PathVariable Long cardId) {
        return cardService.readById(cardId);
    }

    //RETURNS TIME LEFT TO CARD EXPIRATION
    @GetMapping(value = "/")
    public String checkTimeToCardExpiration(@RequestParam ("card_id") Long cardId) {
        return cardService.checkTimeToCardExpiration(cardId).toString();
    }

    @PostMapping(value = "/")
    public void addCard(@RequestBody Card card) throws NumberOfSymbolsDifferentFromRequiredException {
        try{
            cardService.createOrUpdate(card);
        } catch(NullPointerException e) {
            e.printStackTrace();
            e.getMessage();
        } catch(NumberOfSymbolsDifferentFromRequiredException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @PutMapping(value = "/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable Long cardId, @RequestBody Card card) throws
            NumberOfSymbolsDifferentFromRequiredException {
        Card updatedCard = cardService.readById(cardId);
        try {
            updatedCard.setCardNumber(card.getCardNumber());
            updatedCard.setCvv(card.getCvv());
            updatedCard.setHolderName(card.getHolderName());
            updatedCard.setValidTo(card.getValidTo());
            var user = card.getUser();
            updatedCard.setUser(user);
            cardService.createOrUpdate(updatedCard);
        } catch(NullPointerException e) {
            e.printStackTrace();
            e.getMessage();
        } catch(NumberOfSymbolsDifferentFromRequiredException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return ResponseEntity.ok(updatedCard);
    }

}
