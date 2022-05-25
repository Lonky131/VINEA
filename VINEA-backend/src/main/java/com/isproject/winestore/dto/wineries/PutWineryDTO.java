package com.isproject.winestore.dto.wineries;

public class PutWineryDTO {

    private long id;
    private String name;
    private int foundingYear;
    private long regionId;

    public PutWineryDTO(long id, String name, int foundingYear, long regionId) {
        this.id = id;
        this.name = name;
        this.foundingYear = foundingYear;
        this.regionId = regionId;
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
