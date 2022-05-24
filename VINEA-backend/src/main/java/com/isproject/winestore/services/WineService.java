package com.isproject.winestore.services;

import com.isproject.winestore.dto.wine.*;
import com.isproject.winestore.exceptions.DuplicateKeyIdException;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Category;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.WineCategory;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.repos.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<Wine> getWines() {
        return wineRepoJPA.findAll();
    }

    public Wine fetchWineInfo(long wineId) {
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        return wine.get();
    }

    public Wine addWine(AddWineDTO wine) {
        Optional<Winery> wineryEntity = wineryRepoJPA.findById(wine.getWineryId());
        if (wineryEntity.isEmpty()) {
            throw new IdNotExistingException("Winery id not existing!");
        }
        List<WineCategory> wineCategories = new ArrayList<>();
        for (AddWineCategoryDTO addWineCategoryDTO: wine.getCategories()) {
            Optional<Category> category = categoryRepoJPA.findById(addWineCategoryDTO.getCategoryId());
            if (category.isEmpty()) {
                throw new IdNotExistingException("Category id " + addWineCategoryDTO.getCategoryId() +
                        " does not exist!");
            }
            wineCategories.add(new WineCategory(category.get(), addWineCategoryDTO.getValue()));
//            wineCategoryRepoJPA.saveAndFlush(new WineCategory(category.get(), savedWineEntity,
//                    addWineCategoryDTO.getValue()));
        }
        Wine wineEntity = new Wine(wine.getName(), wine.getProductionYear(), wine.getAlcoholPercentage(),
                wine.getVolume(), wine.getPrice(), wine.getPictureUrl(), wineryEntity.get(), wineCategories);
        Wine savedWineEntity = wineRepoJPA.saveAndFlush(wineEntity);
//        List<WineCategory> wineCategories = new ArrayList<>();
//        for (AddWineCategoryDTO addWineCategoryDTO: wine.getCategories()) {
//            Optional<Category> category = categoryRepoJPA.findById(addWineCategoryDTO.getCategoryId());
//            if (category.isEmpty()) {
//                throw new IdNotExistingException("Category id " + addWineCategoryDTO.getCategoryId() +
//                        " does not exist!");
//            }
//            wineCategories.add(new WineCategory(category.get(), addWineCategoryDTO.getValue()));
////            wineCategoryRepoJPA.saveAndFlush(new WineCategory(category.get(), savedWineEntity,
////                    addWineCategoryDTO.getValue()));
//        }
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
        wineEntity1.setProductionYear(wine.getProductionYear());
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

    public boolean addCategoryToWine(long wineId, long categoryId, String value) {
        Optional<Category> category = categoryRepoJPA.findById(categoryId);
        if (category.isEmpty()) {
            throw new IdNotExistingException("Category id does not exist!");
        }
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        Optional<WineCategory> wineCategory = wineCategoryRepoJPA.findByCategoryAndWine(category.get(), wine.get());
        if (wineCategory.isPresent()) {
            throw new DuplicateKeyIdException("Wine already has that category set!");
        }
        WineCategory wineCategory1 = new WineCategory(category.get(), wine.get(), value);
        wine.get().addWineCategory(wineCategory1);
        wineCategoryRepoJPA.saveAndFlush(wineCategory1);
        return true;
    }

    public List<WineCategoryDTO> getWineCategories(long wineId) {
        List<WineCategoryDTO> wineCategoryDTOList = new ArrayList<>();
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty())
            throw new IdNotExistingException("Wine id does not exist!");
        List<WineCategory> wineCategories = wineCategoryRepoJPA.findByWine(wine.get());
        wineCategoryDTOList.addAll(wineCategories.stream().map(wineCategory ->
                new WineCategoryDTO(wineCategory.getId(), wineCategory.getCategory().getId(),
                        wineCategory.getCategory().getName(), wineCategory.getValue())).collect(Collectors.toList()));
        return wineCategoryDTOList;
    }

    public boolean deleteCategoryFromWine(long wineId, long wineCategoryId) {
        Optional<WineCategory> wineCategory = wineCategoryRepoJPA.findById(wineCategoryId);
        if (wineCategory.isEmpty()) {
            throw new IdNotExistingException("Wine category is does not exist!");
        }
        if (wineCategory.get().getWine().getId() != wineId) {
            throw new IdNotExistingException("Wine does not have this category value!");
        }
        wineCategoryRepoJPA.deleteById(wineCategoryId);
        return true;
    }

    public WineCategory updateWineCategory(long wineId, long wineCategoryId, PutWineCategoryDTO putWineCategoryDTO) {
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        Optional<WineCategory> wineCategory = wineCategoryRepoJPA.findById(wineCategoryId);
        if (wineCategory.isEmpty()) {
            throw new IdNotExistingException("Wine category does not exist!");
        }
        WineCategory wineCategory1 = wineCategory.get();
        wineCategory1.setCategory(categoryRepoJPA.findById(putWineCategoryDTO.getCategoryId()).get());
        wineCategory1.setWine(wine.get());
        wineCategory1.setValue(putWineCategoryDTO.getValue());
        return wineCategoryRepoJPA.saveAndFlush(wineCategory1);
    }
}
