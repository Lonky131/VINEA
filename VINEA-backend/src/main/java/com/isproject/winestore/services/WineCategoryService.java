package com.isproject.winestore.services;

import com.isproject.winestore.dto.wine.WineCategoryDTO;
import com.isproject.winestore.exceptions.DuplicateKeyIdException;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Category;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.WineCategory;
import com.isproject.winestore.repos.CategoryRepoJPA;
import com.isproject.winestore.repos.WineCategoryRepoJPA;
import com.isproject.winestore.repos.WineRepoJPA;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WineCategoryService {

    private final WineRepoJPA wineRepoJPA;
    private final CategoryRepoJPA categoryRepoJPA;
    private final WineCategoryRepoJPA wineCategoryRepoJPA;

    public WineCategoryService(WineRepoJPA wineRepoJPA, CategoryRepoJPA categoryRepoJPA, WineCategoryRepoJPA wineCategoryRepoJPA) {
        this.wineRepoJPA = wineRepoJPA;
        this.categoryRepoJPA = categoryRepoJPA;
        this.wineCategoryRepoJPA = wineCategoryRepoJPA;
    }

    public WineCategory addCategoryToWine(long wineId, long categoryId, String value) {
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
        return wineCategoryRepoJPA.saveAndFlush(wineCategory1);
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
            throw new IdNotExistingException("Wine category id does not exist!");
        }
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty())
            throw new IdNotExistingException("Wine id does not exist!");
        if (wineCategory.get().getWine().getId() != wineId) {
            throw new IdNotExistingException("Wine does not have this category value!");
        }
        wine.get().deleteWineCategory(wineCategory.get().getCategory().getId());
        wineCategoryRepoJPA.deleteById(wineCategoryId);
        return true;
    }


    public WineCategory updateWineCategory(long wineId, long wineCategoryId, String value) {
        Optional<Wine> wine = wineRepoJPA.findById(wineId);
        if (wine.isEmpty()) {
            throw new IdNotExistingException("Wine id does not exist!");
        }
        Optional<WineCategory> wineCategory = wineCategoryRepoJPA.findById(wineCategoryId);
        if (wineCategory.isEmpty()) {
            throw new IdNotExistingException("Wine category does not exist!");
        }
        WineCategory wineCategory1 = wineCategory.get();
        wineCategory1.setValue(value);
        wine.get().updateWineCategory(wineCategory.get().getCategory().getId(), value);
        return wineCategoryRepoJPA.saveAndFlush(wineCategory1);
    }
}
