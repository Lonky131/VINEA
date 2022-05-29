package com.isproject.winestore.services;

import com.isproject.winestore.dto.wine.*;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.exceptions.NameAlreadyExistsException;
import com.isproject.winestore.exceptions.YearNotValidException;
import com.isproject.winestore.models.Category;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.WineCategory;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.repos.CategoryRepoJPA;
import com.isproject.winestore.repos.WineCategoryRepoJPA;
import com.isproject.winestore.repos.WineRepoJPA;
import com.isproject.winestore.repos.WineryRepoJPA;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WineService {

    private final WineRepoJPA wineRepoJPA;
    private final WineryRepoJPA wineryRepoJPA;
    private final CategoryRepoJPA categoryRepoJPA;
    private final WineCategoryRepoJPA wineCategoryRepoJPA;
    private final WineCategoryService wineCategoryService;

    public WineService(WineRepoJPA wineRepoJPA, WineryRepoJPA wineryRepoJPA,
                       CategoryRepoJPA categoryRepoJPA, WineCategoryRepoJPA wineCategoryRepoJPA, WineCategoryService wineCategoryService) {
        this.wineryRepoJPA = wineryRepoJPA;
        this.wineRepoJPA = wineRepoJPA;
        this.categoryRepoJPA = categoryRepoJPA;
        this.wineCategoryRepoJPA = wineCategoryRepoJPA;
        this.wineCategoryService = wineCategoryService;
    }


    public List<Wine> getWines() {
        return wineRepoJPA.findAll();
    }

    public WineDTO fetchWineInfo(long wineId) {
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        WineDTO wineDTO = new WineDTO(wine.get());
        List<WineCategoryDTO> wineCategories = wineCategoryService.getWineCategories(wineId);
        wineDTO.setWineCategoryDTOList(wineCategories);
        return wineDTO;
    }

    public Wine addWine(AddWineDTO wine) {
        Optional<Winery> wineryEntity = wineryRepoJPA.findById(wine.getWineryId());
        if (wineryEntity.isEmpty()) {
            throw new IdNotExistingException("Winery id not existing!");
        }
        Optional<Wine> wineEntityName = wineRepoJPA.findByName(wine.getName());
        if (wineEntityName.isPresent()) {
            throw new NameAlreadyExistsException("Wine name already exists!");
        }
        int currentYear = new Date().getYear() + 1900;
        if (wine.getProductionYear() > currentYear || wine.getProductionYear() < 0) {
            throw new YearNotValidException("Wine production year is not valid!");
        }
        List<WineCategory> wineCategories = new ArrayList<>();
        for (AddWineCategoryDTO addWineCategoryDTO: wine.getCategories()) {
            Optional<Category> category = categoryRepoJPA.findById(addWineCategoryDTO.getCategoryId());
            if (category.isEmpty()) {
                throw new IdNotExistingException("Category id " + addWineCategoryDTO.getCategoryId() +
                        " does not exist!");
            }
            wineCategories.add(new WineCategory(category.get(), addWineCategoryDTO.getValue()));
        }
        Wine wineEntity = new Wine(wine.getName(), wine.getProductionYear(), wine.getAlcoholPercentage(),
                wine.getVolume(), wine.getPrice(), wine.getPictureUrl(), wineryEntity.get(), wineCategories);
        Wine savedWineEntity = wineRepoJPA.saveAndFlush(wineEntity);
        return savedWineEntity;
    }

    public boolean deleteWine(long wineId) {
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        wineRepoJPA.deleteById(wineId);
        return true;
    }

    public Wine updateWine(long id, PutWineDTO wine) {
        Optional<Wine> wineEntity = wineRepoJPA.findById(id);
        if (wineEntity.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        Optional<Wine> wineEntityName = wineRepoJPA.findByName(wine.getName());
        if (wineEntityName.isPresent() && !wineEntityName.get().getName().equals(wineEntity.get().getName())) {
            throw new NameAlreadyExistsException("Wine name already exists!");
        }
        int currentYear = new Date().getYear() + 1900;
        if (wine.getProductionYear() > currentYear || wine.getProductionYear() < 0) {
            throw new YearNotValidException("Wine production year is not valid!");
        }
        Wine wineEntity1 = wineEntity.get();
        wineEntity1.setName(wine.getName());
        wineEntity1.setAlcoholPercentage(wine.getAlcoholPercentage());
        wineEntity1.setProductionYear(wine.getProductionYear());
        wineEntity1.setPrice(wine.getPrice());
        wineEntity1.setPictureUrl(wine.getPictureUrl());
        wineEntity1.setVolume(wine.getVolume());
        Optional<Winery> winery = wineryRepoJPA.findById(wine.getWineryId());
        if (winery.isEmpty()) {
            throw new IdNotExistingException("Winery id does not exist!");
        }
        wineEntity1.setWinery(winery.get());
        wineEntity1 = wineRepoJPA.saveAndFlush(wineEntity1);
        return wineEntity1;
    }

}
