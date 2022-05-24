package com.isproject.winestore.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id")
    @SequenceGenerator(name = "category_id", sequenceName = "categories_id_seq", initialValue = 4, allocationSize = 1)
    private long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WineCategory> categories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id")
    @SequenceGenerator(name = "category_id", sequenceName = "categories_id_seq", initialValue = 4, allocationSize = 1)
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
}
