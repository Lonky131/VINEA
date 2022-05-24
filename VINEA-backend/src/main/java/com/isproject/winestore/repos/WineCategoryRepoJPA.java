package com.isproject.winestore.repos;

import com.isproject.winestore.models.WineCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineCategoryRepoJPA extends JpaRepository<WineCategory, Long> {
}
