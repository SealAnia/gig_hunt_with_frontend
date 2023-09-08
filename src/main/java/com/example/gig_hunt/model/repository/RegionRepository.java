package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.Region;
import com.example.gig_hunt.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
