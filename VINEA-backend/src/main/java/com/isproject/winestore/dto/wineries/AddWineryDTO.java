package com.isproject.winestore.dto.wineries;

public class    AddWineryDTO {
    private String name;
    private int foundingYear;
    private long regionId;

    public AddWineryDTO(String name, int foundingYear, long regionId) {
        this.name = name;
        this.foundingYear = foundingYear;
        this.regionId = regionId;
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

    public void setFoundingYear(int founding_year) {
        this.foundingYear = foundingYear;
    }

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

}
