package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Goods;
import com.example.gig_hunt.model.repository.GoodsRepository;
import com.example.gig_hunt.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods readById(Long id) {
        return goodsRepository.findById(id).get();
    }

    @Override
    public Goods createOrUpdate(Goods goods) {
        return goodsRepository.saveAndFlush(goods);
    }

    @Override
    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public List<Goods> findItemsFromMaster(Long userId) {
        return goodsRepository.findItemsFromMaster(userId);
    }

}
