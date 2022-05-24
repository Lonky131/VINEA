package com.isproject.winestore.dto.order;

import java.time.LocalDateTime;

public class AddOrderDTO {
    private LocalDateTime dateAndTime;
    private double totalCost;
    private String address;
    private long userId;

    public AddOrderDTO(LocalDateTime dateAndTime, double totalCost, String address, long userId) {
        this.dateAndTime = dateAndTime;
        this.totalCost = totalCost;
        this.address = address;
        this.userId = userId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double total_cost) {
        this.totalCost = totalCost;
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
