package com.isproject.winestore.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id")
    @SequenceGenerator(name = "orders_id", sequenceName = "orders_id_seq", initialValue = 5, allocationSize = 1)
    private long id;
    private LocalDateTime dateAndTime;
    private double total_cost;
    private String address;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public Order(LocalDateTime dateAndTime, double total_cost, String address, User user) {
        this.dateAndTime = dateAndTime;
        this.total_cost = total_cost;
        this.address = address;
        this.user = user;
    }

    public Order() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id")
    @SequenceGenerator(name = "orders_id", sequenceName = "orders_id_seq", initialValue = 5, allocationSize = 1)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
