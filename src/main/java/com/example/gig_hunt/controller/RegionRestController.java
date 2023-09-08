package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Region;
import com.example.gig_hunt.service.impl.RegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/regions")
public class RegionRestController {

    private final RegionServiceImpl regionService;

    @Autowired
    public RegionRestController(RegionServiceImpl regionService) {
        this.regionService = regionService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Region>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAll());
    }

    @GetMapping(value = "/{regionId}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long regionId) {
        return ResponseEntity.ok(regionService.readById(regionId));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        regionService.createOrUpdate(region);
        return new ResponseEntity<Region>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{regionId}")
    public ResponseEntity<Region> updateRegion(@PathVariable Long regionId, @RequestBody Region region) {
        Region updatedRegion = regionService.readById(regionId);
        updatedRegion.setName(region.getName());
        regionService.createOrUpdate(updatedRegion);
        return ResponseEntity.ok(updatedRegion);
    }

    @DeleteMapping(value = "/{regionId}")
    public void deleteRegion(@PathVariable Long regionId) {
        regionService.delete(regionId);
    }

}
