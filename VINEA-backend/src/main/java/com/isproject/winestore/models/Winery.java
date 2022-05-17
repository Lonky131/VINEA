package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
public class Winery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wineries_id")
    @SequenceGenerator(name = "wineries_id", sequenceName = "wineries_id_seq", initialValue = 5, allocationSize = 1)
    private long id;
    private String name;
    private int foundingYear;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Region region;

    public Winery(String name, int foundingYear, Region region) {
        this.name = name;
        this.foundingYear = foundingYear;
        this.region = region;
    }

    public Winery() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "winery_id")
    @SequenceGenerator(name = "wineries_id", sequenceName = "wineries_id_seq", initialValue = 5, allocationSize = 1)
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

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
