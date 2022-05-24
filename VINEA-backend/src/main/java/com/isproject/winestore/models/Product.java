package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
public class Product {

    @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")
        @SequenceGenerator(name = "product_id", sequenceName = "products_id_seq", initialValue = 58, allocationSize = 1)
        private long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Wine wine;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Order order;

    public Product(Wine wine, Order order) {
        this.wine = wine;
        this.order = order;
    }

    public Product() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")
    @SequenceGenerator(name = "product_id", sequenceName = "products_id_seq", initialValue = 4, allocationSize = 1)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
