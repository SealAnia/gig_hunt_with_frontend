package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.entity.OrderDetails;
import com.example.gig_hunt.model.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserService extends DefaultService<User> {

    Set<Master> findMastersInCategory(Long categoryId);
    Set<Master> findMastersInCategoryAndFromTown(Long categoryId, Long townId);
    Double countEarnedAmount(Long masterId);
    String countEarnedAmountForMonth(Long masterId, String month);

    //NEW
    User findByNickname(String nickname);
    User findByNicknameAndPassword(String nickname, String password);

}
