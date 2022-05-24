package com.isproject.winestore.dto.wineries;

import com.isproject.winestore.dto.region.RegionDTO;
import com.isproject.winestore.models.Winery;

public class WineryDTO {

    private long id;
    private String name;
    private int founding_year;
    private RegionDTO region;

    public WineryDTO(long id, String name, int founding_year, RegionDTO region_id) {
        this.id = id;
        this.name = name;
        this.founding_year = founding_year;
        this.region = region;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFounding_year() {
        return founding_year;
    }

    public void setFounding_year(int founding_year) {
        this.founding_year = founding_year;
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public Winery toWineryEntity() {
        return new Winery(name, founding_year, region.toRegionEntity());
    }
}
