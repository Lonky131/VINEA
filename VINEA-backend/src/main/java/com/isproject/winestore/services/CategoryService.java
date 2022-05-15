package com.isproject.winestore.services;

import com.isproject.winestore.repos.CategoryRepoJPA;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepoJPA categoryRepoJPA;


    public CategoryService(CategoryRepoJPA categoryRepoJPA) {
        this.categoryRepoJPA = categoryRepoJPA;
    }
}
