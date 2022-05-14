package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
@Table(name = "wines")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wines_id")
    @SequenceGenerator(name = "wines_id", sequenceName = "wines_id_seq", initialValue = 15, allocationSize = 1)
    private long id;
    private String name;
    private int productionYear;
    private double alcoholPercentage;
    private int volume;
    private double price;
    private String pictureUrl;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Winery winery;

    public Wine(String name, int productionYear, double alcoholPercentage, int volume, double price,
                String pictureUrl, Winery winery) {
        this.name = name;
        this.productionYear = productionYear;
        this.alcoholPercentage = alcoholPercentage;
        this.volume = volume;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.winery = winery;
    }

    public Wine() {

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

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public Winery getWinery() {
        return winery;
    }

    public void setWinery(Winery winery) {
        this.winery = winery;
    }
}
