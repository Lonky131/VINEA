package com.isproject.winestore.services;

import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.repos.WineRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineService {

    private final WineRepo wineRepo;

    public WineService(WineRepo wineRepo) {
        this.wineRepo = wineRepo;
    }


    public List<Wine> getWines() {
        return wineRepo.getWines();
    }

    public void fetchWineInfo(long wineId) {

    }

    public void addWine(WineDTO wine) {

    }

    public void deleteWine(long wineId) {

    }

    public void updatingWine(WineDTO wine) {

    }

    public void fetchNewestWines() {

    }

    public void fetchOnSaleWines() {

    }



}
