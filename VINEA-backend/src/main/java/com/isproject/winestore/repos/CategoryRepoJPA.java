package com.isproject.winestore.repos;

import com.isproject.winestore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepoJPA extends JpaRepository<Category, Long> {
}
