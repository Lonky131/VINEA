package com.isproject.winestore.services;

import com.isproject.winestore.dto.wine.AddWineDTO;
import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.repos.WineRepoJPA;
import com.isproject.winestore.repos.WineRepoJdbc;
import com.isproject.winestore.repos.WineryRepoJPA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineService {

    private final WineRepoJdbc wineRepoJdbc;
    private final WineRepoJPA wineRepoJPA;
    private final WineryRepoJPA wineryRepoJPA;

    public WineService(WineRepoJdbc wineRepoJdbc, WineRepoJPA wineRepoJPA, WineryRepoJPA wineryRepoJPA) {
        this.wineryRepoJPA = wineryRepoJPA;
        this.wineRepoJdbc = wineRepoJdbc;
        this.wineRepoJPA = wineRepoJPA;
    }


    public List<WineDTO> getWines() {
        return wineRepoJdbc.getWines();
    }

    public void fetchWineInfo(long wineId) {

    }

    public Wine addWine(AddWineDTO wine) {
        Winery wineryEntity = wineryRepoJPA.getById(wine.getWineryId());
        Wine wineEntity = new Wine(wine.getName(), wine.getProductionYear(), wine.getAlcoholPercentage(),
                wine.getVolume(), wine.getPrice(), wine.getPictureUrl(), wineryEntity);
        return wineRepoJPA.saveAndFlush(wineEntity);
    }

    public void deleteWine(long wineId) {

    }

    public void updatingWine(WineDTO wine) {

    }

    public void fetchNewestWines() {

    }

    public void fetchOnSaleWines() {

    }


    public List<WineDTO> getWinesByName(String name) {
        return wineRepoJdbc.getWinesByName(name);
    }

    public List<WineDTO> getWinesByColor(String color) {
        return wineRepoJdbc.getWinesByColor(color);
    }
}
