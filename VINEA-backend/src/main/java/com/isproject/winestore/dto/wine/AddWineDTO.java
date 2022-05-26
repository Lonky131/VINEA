package com.isproject.winestore.dto.wine;

import com.isproject.winestore.models.Wine;

import java.util.ArrayList;
import java.util.List;

public class AddWineDTO {

    private String name;
    private int productionYear;
    private double alcoholPercentage;
    private int volume;
    private double price;
    private String pictureUrl;
    private long wineryId;

    private List<AddWineCategoryDTO> categories = new ArrayList<>();

    public AddWineDTO(String name, int productionYear, double alcoholPercentage, int volume,
                      double price, String pictureUrl, long wineryId, List<AddWineCategoryDTO> addWineCategoriesDTO) {
        this.name = name;
        this.productionYear = productionYear;
        this.alcoholPercentage = alcoholPercentage;
        this.volume = volume;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.wineryId = wineryId;
        this.categories = addWineCategoriesDTO;
    }

    public AddWineDTO(Wine wine) {
        this.name = wine.getName();
        this.productionYear = wine.getProductionYear();
        this.alcoholPercentage = wine.getAlcoholPercentage();
        this.volume = wine.getVolume();
        this.price = wine.getPrice();
        this.pictureUrl = wine.getPictureUrl();
        this.wineryId = wine.getWinery().getId();
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

    public List<AddWineCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<AddWineCategoryDTO> categories) {
        this.categories = categories;
    }
}
