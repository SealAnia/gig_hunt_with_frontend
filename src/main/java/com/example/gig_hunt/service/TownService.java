package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Town;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TownService extends DefaultService<Town> {

    List<Town> sortByNameAsc(Sort sort);
    List<Town> sortByNameDesc(Sort sort);

    Set<Town> sortTownsByRegionNameASC();
    Set<Town> sortTownsByRegionNameDESC();

    //List<Town> getTownsOfARegion(String region);
    Set<Town> getTownsOfARegion(String region);

}
