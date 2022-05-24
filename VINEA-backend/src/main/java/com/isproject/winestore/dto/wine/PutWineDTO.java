package com.isproject.winestore.dto.wine;

public class PutWineDTO {

    private long id;
    private String name;
    private int productionYear;
    private double alcoholPercentage;
    private int volume;
    private double price;
    private String pictureUrl;
    private long wineryId;

    public PutWineDTO(long id, String name, int productionYear, double alcoholPercentage, int volume,
                      double price, String pictureUrl, long wineryId) {
        this.id = id;
        this.name = name;
        this.productionYear = productionYear;
        this.alcoholPercentage = alcoholPercentage;
        this.volume = volume;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.wineryId = wineryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public int getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public long getWineryId() {
        return wineryId;
    }


}
