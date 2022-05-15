package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id")
    @SequenceGenerator(name = "category_id", sequenceName = "categories_id_seq", initialValue = 4, allocationSize = 1)
    private long id;
    private String name;

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
