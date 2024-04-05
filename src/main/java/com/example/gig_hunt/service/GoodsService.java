package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Goods;

import java.util.List;
import java.util.Set;

public interface GoodsService extends DefaultService<Goods> {

    Set<Goods> findItemsFromMaster(Long userId);

}
