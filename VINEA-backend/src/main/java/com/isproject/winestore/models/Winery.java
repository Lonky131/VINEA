package com.isproject.winestore.models;

public class Winery {

    private long id;
    private String name;
    private int founding_year;
    private long region_id;

    public Winery(long id, String name, int founding_year, long region_id) {
        this.id = id;
        this.name = name;
        this.founding_year = founding_year;
        this.region_id = region_id;
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

    public long getRegion_id() {
        return region_id;
    }

    public void setRegion_id(long region_id) {
        this.region_id = region_id;
    }
}
