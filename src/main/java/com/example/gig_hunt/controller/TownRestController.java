package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Region;
import com.example.gig_hunt.model.entity.Town;
import com.example.gig_hunt.service.impl.RegionServiceImpl;
import com.example.gig_hunt.service.impl.TownServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/towns")
public class TownRestController {

    private final TownServiceImpl townService;
    @Autowired
    public TownRestController(TownServiceImpl townService) {
        this.townService = townService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Town>> getAllTowns() {
        return ResponseEntity.ok(townService.getAll());
    }

    @GetMapping(value = "/{townId}")
    public ResponseEntity<Town> getTownById(@PathVariable Long townId) {
        return ResponseEntity.ok(townService.readById(townId));
    }

    @GetMapping(value = "/sorted_by_name_asc")
    public ResponseEntity<List<Town>> sortTowmsByNameAsc() {
        return ResponseEntity.ok(townService.sortByNameAsc(Sort.by(Sort.Direction.ASC, "name")));
    }

    @GetMapping(value = "/sorted_by_name_desc")
    public ResponseEntity<List<Town>> sortTownsByNameDesc() {
        return ResponseEntity.ok(townService.sortByNameDesc(Sort.by(Sort.Direction.DESC, "name")));
    }

    @GetMapping(value = "/sorted_by_region_asc")
    public ResponseEntity<List<Town>> sortTownsByRegionAsc() {
        return ResponseEntity.ok(townService.sortTownsByRegionNameASC());
    }

    @GetMapping(value = "/sorted_by_region_desc")
    public ResponseEntity<List<Town>> sortTownsByRegionDesc() {
        return ResponseEntity.ok(townService.sortTownsByRegionNameDESC());
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Town>> getTownsOfARegion(@RequestParam String region) {
        return ResponseEntity.ok(townService.getTownsOfARegion(region));
    }

    @PostMapping(value = "/")
    public void createTown(@RequestBody Town town) {
        townService.createOrUpdate(town);
    }

    @PutMapping(value = "/{townId}")
    public ResponseEntity<Town> updateTown(@PathVariable Long townId, @RequestBody Town town) {
        Town updatedTown = townService.readById(townId);

        var region = town.getRegion();
        updatedTown.setRegion(region);

        updatedTown.setName(town.getName());
        townService.createOrUpdate(updatedTown);
        return ResponseEntity.ok(updatedTown);
    }

    @DeleteMapping(value = "/{townId}")
    public void deleteTown(@PathVariable Long townId) {
        townService.delete(townId);
    }

}
