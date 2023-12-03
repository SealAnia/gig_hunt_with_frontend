package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long cardId;

    @Column(name = "card_number", nullable = false)
    @ToString.Include
    private Long cardNumber;

    @Column(name = "cvv", columnDefinition = "bigint", nullable = false, length = 3)
    @ToString.Include
    private int cvv;

    @Column(name = "holder_name", columnDefinition = "varchar(45)", nullable = false)
    @ToString.Include
    private String holderName;

    @Column(name = "valid_to", columnDefinition = "date", nullable = false)
    @ToString.Include
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    private Date validTo;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private User user;

    @Column(name = "total_amount", columnDefinition = "double default = 0.0", nullable = false)
    private Double totalAmount;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(cardId);
        result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
        result = prime * result + ((cvv == 0) ? 0 : cvv);
        result = prime * result + ((holderName == null) ? 0 : holderName.hashCode());
        result = prime * result + ((validTo == null) ? 0 : validTo.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    public boolean equals(Card card) {
        if(card == this) {
            return true;
        }
        if(card == null || card.getClass() != this.getClass()) {
            return false;
        }
        Card cardTwo = (Card) this;
        return cardId == cardTwo.cardId &&
                (cardNumber == cardTwo.cardNumber || (cardNumber != null && cardNumber.equals(cardTwo.cardNumber))) &&
                cvv == cardTwo.cvv || (cvv != 0 && cvv == cardTwo.cvv) &&
                (holderName == cardTwo.holderName || (holderName != null && holderName.equals(cardTwo.holderName))) &&
                (validTo == cardTwo.validTo || (validTo != null && validTo.equals(cardTwo.validTo))) &&
                (user == cardTwo.user || (user != null && user.equals(cardTwo.user)));
    }

}