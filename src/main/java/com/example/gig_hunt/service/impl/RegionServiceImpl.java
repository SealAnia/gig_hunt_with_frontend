package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Region;
import com.example.gig_hunt.model.repository.RegionRepository;
import com.example.gig_hunt.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region readById(Long id) {
        return regionRepository.findById(id).get();
    }

    @Override
    public Region createOrUpdate(Region region) {
        return regionRepository.saveAndFlush(region);
    }

    @Override
    public void delete(Long id) {
        regionRepository.deleteById(id);
    }

}
