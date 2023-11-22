package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Town;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TownService extends DefaultService<Town> {

    List<Town> sortByNameAsc(Sort sort);
    List<Town> sortByNameDesc(Sort sort);

    List<Town> sortTownsByRegionNameASC();
    List<Town> sortTownsByRegionNameDESC();

    List<Town> getTownsOfARegion(String region);

}
