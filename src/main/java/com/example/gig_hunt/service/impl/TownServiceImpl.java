package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Town;
import com.example.gig_hunt.model.repository.TownRepository;
import com.example.gig_hunt.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public List<Town> getAll() {
        return townRepository.findAll();
    }

    @Override
    public Town readById(Long id) {
        return townRepository.findById(id).get();
    }

    @Override
    public Town createOrUpdate(Town town) {
        return townRepository.saveAndFlush(town);
    }

    @Override
    public void delete(Long id) {
        townRepository.deleteById(id);
    }

}
