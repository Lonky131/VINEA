package com.isproject.winestore.dto.wineries;

import com.isproject.winestore.dto.region.RegionDTO;
import com.isproject.winestore.models.Winery;

public class WineryDTO {

    private long id;
    private String name;
    private int foundingYear;
    private RegionDTO region;

    public WineryDTO(long id, String name, int foundingYear, RegionDTO region) {
        this.id = id;
        this.name = name;
        this.foundingYear = foundingYear;
        this.region = region;
    }
    
    public WineryDTO(Winery winery) {
        this.id = winery.getId();
        this.name = winery.getName();
        this.foundingYear = winery.getFoundingYear();
        this.region = new RegionDTO(winery.getRegion());
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

    public int getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public Winery toWineryEntity() {
        return new Winery(name, foundingYear, region.toRegionEntity());
    }
}
