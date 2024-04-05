package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
        result = prime * result + Long.hashCode(getCardId());
        result = prime * result + ((getCardNumber() == null) ? 0 : getCardNumber().hashCode());
        result = prime * result + ((getCvv() == 0) ? 0 : getCvv());
        result = prime * result + ((getHolderName() == null) ? 0 : getHolderName().hashCode());
        result = prime * result + ((getValidTo() == null) ? 0 : getValidTo().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
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
        return getCardId() == cardTwo.getCardId() &&
                (getCardNumber() == cardTwo.getCardNumber() || (getCardNumber() != null && getCardNumber().equals(cardTwo.getCardNumber()))) &&
                getCvv() == cardTwo.getCvv() || (getCvv() != 0 && getCvv() == cardTwo.getCvv()) &&
                (getHolderName() == cardTwo.getHolderName() || (getHolderName() != null && getHolderName().equals(cardTwo.getHolderName()))) &&
                (getValidTo() == cardTwo.getValidTo() || (getValidTo() != null && getValidTo().equals(cardTwo.getValidTo()))) &&
                (getUser() == cardTwo.getUser() || (getUser() != null && getUser().equals(cardTwo.getUser())));
    }

}