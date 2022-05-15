package com.isproject.winestore.dto.region;

import com.isproject.winestore.models.Region;

public class RegionDTO {

    private long id;
    private String name;
    private String country;

    public RegionDTO(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public Region toRegionEntity() {
        return new Region(name, country);
    }
}
