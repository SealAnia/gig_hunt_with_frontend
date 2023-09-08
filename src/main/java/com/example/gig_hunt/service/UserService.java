package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.entity.OrderDetails;
import com.example.gig_hunt.model.entity.User;

import java.util.Date;
import java.util.List;

public interface UserService extends DefaultService<User> {

    List<OrderDetails> findOrdersForUser(Long userId);
    List<Master> findMastersInCategory(Long categoryId);
    List<Master> findMastersInCategoryAndFromTown(Long categoryId, Long townId);
    Double countEarnedAmount(Long masterId);
    String countEarnedAmountForMonth(Long masterId, String month);

}
