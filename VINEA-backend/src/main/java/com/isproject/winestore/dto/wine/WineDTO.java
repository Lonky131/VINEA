package com.isproject.winestore.dto.wine;

import com.isproject.winestore.dto.wineries.WineryDTO;
import com.isproject.winestore.models.Wine;

import java.util.ArrayList;
import java.util.List;

public class WineDTO {

    private long id;
    private String name;
    private int productionYear;
    private double alcoholPercentage;
    private int volume;
    private double price;
    private String pictureUrl;
    private WineryDTO winery;
    private List<WineCategoryDTO> wineCategoryDTOList = new ArrayList<>();

    public WineDTO(long id, String name, int productionYear, double alcoholPercentage, int volume, double price,
                   String pictureUrl, WineryDTO winery) {
        this.id = id;
        this.name = name;
        this.productionYear = productionYear;
        this.alcoholPercentage = alcoholPercentage;
        this.volume = volume;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.winery = winery;
    }

    public WineDTO(Wine wine) {
        this.id = wine.getId();
        this.name = wine.getName();
        this.productionYear = wine.getProductionYear();
        this.alcoholPercentage = wine.getAlcoholPercentage();
        this.volume = wine.getVolume();
        this.price = wine.getPrice();
        this.pictureUrl = wine.getPictureUrl();
        this.winery = new WineryDTO(wine.getWinery());
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

    public void setName(String name) {
        this.name = name;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public WineryDTO getWinery() {
        return winery;
    }

    public void setWinery(WineryDTO winery) {
        this.winery = winery;
    }

    public List<WineCategoryDTO> getWineCategoryDTOList() {
        return wineCategoryDTOList;
    }

    public void setWineCategoryDTOList(List<WineCategoryDTO> wineCategoryDTOList) {
        this.wineCategoryDTOList = wineCategoryDTOList;
    }

    public void addCategory(WineCategoryDTO wineCategoryDTO) {
        wineCategoryDTOList.add(wineCategoryDTO);
    }

    public Wine toWineEntity() {
        return new Wine(name, productionYear, alcoholPercentage, volume, price,
                pictureUrl, winery.toWineryEntity());
    }
}
