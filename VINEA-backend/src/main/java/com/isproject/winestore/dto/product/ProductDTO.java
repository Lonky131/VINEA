package com.isproject.winestore.dto.product;

public class ProductDTO {

    private long id;
    private long wineId;
    private long orderId;

    public ProductDTO(long id, long wineId, long orderId) {
        this.id = id;
        this.wineId = wineId;
        this.orderId = orderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWineId() {
        return wineId;
    }

    public void setWineId(long wineId) {
        this.wineId = wineId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
