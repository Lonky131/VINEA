package com.isproject.winestore.controllers;


import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.exceptions.NameAlreadyExistsException;
import com.isproject.winestore.models.Category;
import com.isproject.winestore.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestParam(required = true) String name) {
        logger.info("Adding category with name: " + name + "...");
        Category category = categoryService.addCategory(name);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/" + category.getId())
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
        logger.info("Fetching category with id: " + id + "...");
        return new ResponseEntity<Category>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        logger.info("Fetching all categories...");
        return new ResponseEntity<List<Category>>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {IdNotExistingException.class, NameAlreadyExistsException.class})
    public ResponseEntity<String> handleException(IdNotExistingException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
