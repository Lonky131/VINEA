package com.isproject.winestore.controllers;

import com.isproject.winestore.dto.region.AddRegionDTO;
import com.isproject.winestore.models.Region;
import com.isproject.winestore.services.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/region")
public class RegionController {

    private final RegionService regionService;
    private final static Logger logger = LoggerFactory.getLogger(RegionController.class);


    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Region> getRegion(@PathVariable long id) {
        logger.info("Fetching region with id " + id + "...");
        return new ResponseEntity<Region>(regionService.getRegionById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        logger.info("Fetching all regions...");
        return new ResponseEntity<List<Region>>(regionService.getAllRegions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addRegion(@RequestBody AddRegionDTO addRegionDTO) {
        logger.info("Adding new region...");
        Region region = regionService.addRegion(addRegionDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/" + region.getId())
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
