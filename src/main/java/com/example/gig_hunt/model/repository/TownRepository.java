package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    @Query(value = "SELECT t.town_id, t.name, t.region_id FROM town t INNER JOIN region r ON r.region_id = t.region_id " +
            "ORDER BY r.name ASC", nativeQuery = true)
    List<Town> sortTownsByRegionNameASC();

    @Query(value = "SELECT t.town_id, t.name, t.region_id FROM town t INNER JOIN region r ON r.region_id = t.region_id " +
            "ORDER BY r.name DESC", nativeQuery = true)
    List<Town> sortTownsByRegionNameDESC();

    @Query(value = "SELECT t.town_id, t.name, t.region_id FROM town t INNER JOIN region r ON t.region_id = r.region_id " +
            "WHERE r.name = :region", nativeQuery = true)
    List<Town> getTownsOfARegion(@Param("region") String region);

}
