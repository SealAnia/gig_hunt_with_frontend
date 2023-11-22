package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Region;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RegionService extends DefaultService<Region> {

    List<Region> sortByNameAsc(Sort sort);
    List<Region> sortByNameDesc(Sort sort);

}
