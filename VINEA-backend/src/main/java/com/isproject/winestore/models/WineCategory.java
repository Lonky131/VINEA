package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
public class WineCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wine_category_id")
    @SequenceGenerator(name = "wine_category_id", sequenceName = "wine_category_id_seq", initialValue = 22, allocationSize = 1)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wine_id")
    private Wine wine;

    private String value;

    public WineCategory(Category category, Wine wine, String value) {
        this.category = category;
        this.wine = wine;
        this.value = value;
    }

    public WineCategory() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
