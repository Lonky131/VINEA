package com.isproject.winestore.services;

import com.isproject.winestore.dto.wine.AddWineDTO;
import com.isproject.winestore.dto.wine.PutWineDTO;
import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Category;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.repos.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineService {

    private final WineRepoJdbc wineRepoJdbc;
    private final WineRepoJPA wineRepoJPA;
    private final WineryRepoJPA wineryRepoJPA;
    private final CategoryRepoJPA categoryRepoJPA;
    private final WineCategoryRepoJPA wineCategoryRepoJPA;

    public WineService(WineRepoJdbc wineRepoJdbc, WineRepoJPA wineRepoJPA, WineryRepoJPA wineryRepoJPA,
                       CategoryRepoJPA categoryRepoJPA, WineCategoryRepoJPA wineCategoryRepoJPA) {
        this.wineryRepoJPA = wineryRepoJPA;
        this.wineRepoJdbc = wineRepoJdbc;
        this.wineRepoJPA = wineRepoJPA;
        this.categoryRepoJPA = categoryRepoJPA;
        this.wineCategoryRepoJPA = wineCategoryRepoJPA;
    }


    public List<WineDTO> getWines() {
        return wineRepoJdbc.getWines();
    }

    public void fetchWineInfo(long wineId) {

    }

    public Wine addWine(AddWineDTO wine) {
        Optional<Winery> wineryEntity = wineryRepoJPA.findById(wine.getWineryId());
        if (wineryEntity.isEmpty()) {
            throw new IdNotExistingException("Winery id not existing!");
        }
        Wine wineEntity = new Wine(wine.getName(), wine.getProductionYear(), wine.getAlcoholPercentage(),
                wine.getVolume(), wine.getPrice(), wine.getPictureUrl(), wineryEntity.get());
        return wineRepoJPA.saveAndFlush(wineEntity);
    }

    public void deleteWine(long wineId) {
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        wineRepoJPA.deleteById(wineId);
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

    public Wine updateWine(long id, PutWineDTO wine) {
        Optional<Wine> wineEntity = wineRepoJPA.findById(id);
        if (wineEntity.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        Wine wineEntity1 = wineEntity.get();
        wineEntity1.setName(wine.getName());
        wineEntity1.setAlcoholPercentage(wine.getAlcoholPercentage());
        wineEntity1.setPrice(wine.getPrice());
        wineEntity1.setPictureUrl(wine.getPictureUrl());
        wineEntity1.setVolume(wine.getVolume());
        Optional<Winery> winery = wineryRepoJPA.findById(wine.getWineryId());
        if (winery.isEmpty()) {
            throw new IdNotExistingException("Winery id does not exist!");
        }
        wineEntity1.setWinery(winery.get());
        return wineRepoJPA.saveAndFlush(wineEntity1);
    }

    public void addCategoryToWine(long wineId, long categoryId, String value) {
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        Optional<Category> category = categoryRepoJPA.findById(categoryId);
        if (category.isEmpty()) {
            throw new IdNotExistingException("Category id does not exist!");
        }


    }
}
