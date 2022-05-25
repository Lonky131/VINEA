package com.isproject.winestore.repos;

import com.isproject.winestore.models.Category;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.WineCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WineCategoryRepoJPA extends JpaRepository<WineCategory, Long> {

//    Optional<WineCategory> findWineCategoryByCategoryAndWine(Category category, Wine wine);
    Optional<WineCategory> findByCategoryAndWine(Category category, Wine wine);
    List<WineCategory> findByWine(Wine wine);

}
