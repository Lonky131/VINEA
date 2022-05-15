package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regions_id")
    @SequenceGenerator(name = "regions_id", sequenceName = "regions_id_seq", initialValue = 5, allocationSize = 1)
    private long id;
    private String name;
    private String country;

    public Region(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Region() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regions_id")
    @SequenceGenerator(name = "regions_id", sequenceName = "regions_id_seq", initialValue = 5, allocationSize = 1)
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
