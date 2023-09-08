package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Goods;

import java.util.List;

public interface GoodsService extends DefaultService<Goods> {

    List<Goods> findItemsFromMaster(Long userId);

}
