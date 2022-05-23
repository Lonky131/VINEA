package com.isproject.winestore.controllers;

import com.isproject.winestore.dto.wine.AddWineDTO;
import com.isproject.winestore.dto.wine.PutWineDTO;
import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.services.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Controller
@RequestMapping(value = "/wine")
public class WineController {

    private final WineService wineService;
    private final static Logger logger = LoggerFactory.getLogger(WineController.class);


    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

//    @GetMapping
//    public ResponseEntity<List<WineDTO>> getWines(
//            @RequestParam(required = false) Optional<String> name,
//            @RequestParam(required = false) Optional<String> color,
//            @RequestParam(required = false) Optional<String> type,
//            @RequestParam(required = false) Optional<String> sweetness,
//            @RequestParam(required = false) Optional<String>[] wineries) {
//
//        logger.info("Fetching all wines");
//        List<WineDTO> wines;
//        if (name.isPresent()) {
//            wines = wineService.getWinesByName(name.get());
//        } else if (color.isPresent()) {
//            wines = wineService.getWinesByColor(color.get());
//        } else {
//            wines = wineService.getWines();
//        }
//        return new ResponseEntity<List<WineDTO>>(wines, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Wine>> getAllWines() {
        logger.info("Fetching all wines...");
        return new ResponseEntity<List<Wine>>(wineService.getWines(), HttpStatus.OK);
    }


    @GetMapping(value = "/{wineId}")
    public ResponseEntity<WineDTO> getWine(@PathVariable Long wineId) {
        //info o proizvodu
        logger.info("Fetching info about wine...");
        wineService.fetchWineInfo(wineId);

        return new ResponseEntity(wineService.fetchWineInfo(wineId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addWine(@RequestBody AddWineDTO wine) {
        //dodat vino
        logger.info("Adding wine...");
        Wine wineEntity = wineService.addWine(wine);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/" + wineEntity.getId())
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/add-category/{wineId}")
    public ResponseEntity addCategoryToWine(@RequestParam(required = true) long categoryId,
                                            @RequestParam(required = true) String value,
                                            @PathVariable long wineId) {
        logger.info("Adding value " + value + " of category " + categoryId + " to wine " + wineId + "...");
        wineService.addCategoryToWine(wineId, categoryId, value);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{wineId}")
    public ResponseEntity deleteWine(@PathVariable long wineId) {
        //brisanje vina
        logger.info("Deleting wine " + wineId + "...");
        wineService.deleteWine(wineId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Wine> updateWine(@PathVariable long id, @RequestBody PutWineDTO wine) {
        logger.info("Updating wine with id " + id + "...");
        return new ResponseEntity<Wine>(wineService.updateWine(id, wine), HttpStatus.OK);
    }


    @ExceptionHandler(value = IdNotExistingException.class)
    public ResponseEntity<String> handleException(IdNotExistingException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



//    @GetMapping(value = "/search")
//    public ResponseEntity<List<WineDTO>> searchWines(@RequestBody)
//    @PutMapping(value = "/")
//    public ResponseEntity<WineDTO> updateWine(@RequestBody WineDTO wine) {
//        //update vina
//        logger.info("Updating wine...");
//        wineService.updatingWine(wine);
//
//        return new ResponseEntity(new WineDTO(), HttpStatus.OK);
//    }


    //najprodavanije? najbolje ocjenjeno?



}
