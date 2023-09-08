package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT CONCAT('Your card expires: ', DATE_FORMAT(c.valid_to, '%y/%m'), ' time to expire left: ', " +
            "DATEDIFF(c.valid_to, NOW())) FROM card c WHERE c.card_id= :card_id", nativeQuery = true)
    String checkTimeToCardExpiration(@Param("card_id") Long cardId);

}
