package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
@Table(name = "wineries")
public class Winery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private int founding_year;

    @ManyToOne
    private Region region;

    public Winery(long id, String name, int founding_year, Region region) {
        this.id = id;
        this.name = name;
        this.founding_year = founding_year;
        this.region = region;
    }

    public Winery() {

    }
}
