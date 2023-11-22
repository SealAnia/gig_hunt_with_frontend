package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Town;
import com.example.gig_hunt.model.repository.TownRepository;
import com.example.gig_hunt.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<Town> sortByNameAsc(Sort sort) {
        List<Town> towns = townRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return towns;
    }

    @Override
    public List<Town> sortByNameDesc(Sort sort) {
        List<Town> towns = townRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        return towns;
    }

    @Override
    public List<Town> sortTownsByRegionNameASC() {
        return townRepository.sortTownsByRegionNameASC();
    }

    @Override
    public List<Town> sortTownsByRegionNameDESC() {
        return townRepository.sortTownsByRegionNameDESC();
    }

    @Override
    public List<Town> getTownsOfARegion(String region) {
        return townRepository.getTownsOfARegion(region);
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
