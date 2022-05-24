package com.isproject.winestore.dto.order;

import java.time.LocalDateTime;

public class OrderDTO {

    private long id;
    private LocalDateTime dateAndTime;
    private double total_cost;
    private String address;
    private long userId;

    public OrderDTO(long id, LocalDateTime dateAndTime, double total_cost, String address, long userId) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.total_cost = total_cost;
        this.address = address;
        this.userId = userId;
    }

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
