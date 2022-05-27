package com.isproject.winestore.controllers;

import com.isproject.winestore.dto.wine.WineCategoryDTO;
import com.isproject.winestore.exceptions.DuplicateKeyIdException;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.WineCategory;
import com.isproject.winestore.services.WineCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/wine-category")
public class WineCategoryController {

    private final WineCategoryService wineCategoryService;
    private final static Logger logger = LoggerFactory.getLogger(WineCategoryController.class);


    public WineCategoryController(WineCategoryService wineCategoryService) {
        this.wineCategoryService = wineCategoryService;
    }

    @GetMapping(value = "/{wineId}")
    public ResponseEntity<List<WineCategoryDTO>> getWineCategories(@PathVariable long wineId) {
        logger.info("Fetching categories for wine " + wineId + "...");
        return new ResponseEntity<List<WineCategoryDTO>>(wineCategoryService.getWineCategories(wineId), HttpStatus.OK);
    }

    @PostMapping(value = "/{wineId}")
    public ResponseEntity addCategoryToWine(@RequestParam(required = true) long categoryId,
                                            @RequestParam(required = true) String value,
                                            @PathVariable long wineId) {
        logger.info("Adding value " + value + " of category " + categoryId + " to wine " + wineId + "...");
        wineCategoryService.addCategoryToWine(wineId, categoryId, value);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{wineId}")
    public ResponseEntity deleteCategoryFromWine(@PathVariable long wineId, @RequestParam long wineCategoryId) {
        logger.info("Deleting category with value id " + wineCategoryId + " from wine " + wineId + "...");
        wineCategoryService.deleteCategoryFromWine(wineId, wineCategoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{wineId}")
    public ResponseEntity<WineCategory> updateWineCategory(@PathVariable long wineId,
                                                           @RequestParam long wineCategoryId,
                                                           @RequestParam String value) {
        logger.info("Updating wine " + wineId + "...");
        return new ResponseEntity<WineCategory>(
                wineCategoryService.updateWineCategory(wineId, wineCategoryId, value), HttpStatus.OK);
    }


    @ExceptionHandler(value = {IdNotExistingException.class, DuplicateKeyIdException.class})
    public ResponseEntity<String> handleException(RuntimeException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
