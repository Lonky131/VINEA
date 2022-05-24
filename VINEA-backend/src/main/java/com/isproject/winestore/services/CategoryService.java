package com.isproject.winestore.services;

import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.exceptions.NameAlreadyExistsException;
import com.isproject.winestore.models.Category;
import com.isproject.winestore.repos.CategoryRepoJPA;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepoJPA categoryRepoJPA;


    public CategoryService(CategoryRepoJPA categoryRepoJPA) {
        this.categoryRepoJPA = categoryRepoJPA;
    }

    public Category addCategory(String name) {
        Optional<Category> category = categoryRepoJPA.findByName(name);
        if (category.isPresent()) {
            throw new NameAlreadyExistsException("Name " + name + " already exists!");
        }
        return categoryRepoJPA.saveAndFlush(new Category(name));
    }

    public Category getCategoryById(long id) {
        Optional<Category> category = categoryRepoJPA.findById(id);
        if (category.isEmpty()) {
            throw new IdNotExistingException("Category id " + id + "already exists!");
        }
        return category.get();
    }

    public List<Category> getAllCategories() {
        return categoryRepoJPA.findAll();
    }
}
