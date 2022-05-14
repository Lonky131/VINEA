package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regions_id")
    @SequenceGenerator(name = "regions_id", sequenceName = "regions_id_seq", initialValue = 5, allocationSize = 1)
    @JoinColumn(name = "id")
    private long id;
    private String name;

    public Region(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Region() {

    }
}
