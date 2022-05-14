package com.isproject.winestore.dto.region;

import com.isproject.winestore.models.Region;

public class RegionDTO {

    private long id;
    private String name;

    public RegionDTO(long id, String name) {
        this.id = id;
        this.name = name;
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
        return new Region(0, name);
    }
}
