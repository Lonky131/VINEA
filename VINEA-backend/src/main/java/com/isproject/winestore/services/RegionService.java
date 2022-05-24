package com.isproject.winestore.services;

import com.isproject.winestore.dto.region.AddRegionDTO;
import com.isproject.winestore.models.Region;
import com.isproject.winestore.repos.RegionRepoJPA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepoJPA regionRepoJPA;

    public RegionService(RegionRepoJPA regionRepoJPA) {
        this.regionRepoJPA = regionRepoJPA;
    }


    public Region getRegionById(long id) {
        return regionRepoJPA.getById(id);
    }

    public List<Region> getAllRegions() {
        return regionRepoJPA.findAll();
    }

    public Region addRegion(AddRegionDTO addRegionDTO) {
        return regionRepoJPA.saveAndFlush(new Region(addRegionDTO.getName(), addRegionDTO.getCountry()));
    }
}
