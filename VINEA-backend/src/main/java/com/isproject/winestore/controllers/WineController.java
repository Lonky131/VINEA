package com.isproject.winestore.controllers;

import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.services.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/wine", consumes = "application/json")
public class WineController {

    private final WineService wineService;
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping(value = "/{wineId}")
    public ResponseEntity<WineDTO> getWine(@PathVariable Long wineId) {
        //info o proizvodu
        logger.info("Fetching info about wine...");
        wineService.fetchWineInfo(wineId);

        return new ResponseEntity(new WineDTO(), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity addWine(@RequestBody WineDTO wine) {
        //dodat vino
        logger.info("Adding wine...");
        wineService.addWine(wine);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{wineId}")
    public ResponseEntity deleteWine(@PathVariable long wineId) {
        //brisanje vina
        logger.info("Deleting wine...");
        wineService.deleteWine(wineId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/")
    public ResponseEntity<WineDTO> updateWine(@RequestBody WineDTO wine) {
        //update vina
        logger.info("Updating wine...");
        wineService.updatingWine(wine);

        return new ResponseEntity(new WineDTO(), HttpStatus.OK);
    }

    @GetMapping(value = "/new")
    public ResponseEntity newWines() {
        //dohvacanje najnovijih vina
        logger.info("Fetching newest wines...");
        wineService.fetchNewestWines();

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/on-sale")
    public ResponseEntity onSaleWines() {
        //vina na popustu
        logger.info("Fetching wines on sale...");
        wineService.fetchOnSaleWines();

        return new ResponseEntity(HttpStatus.OK);
    }


    //najprodavanije? najbolje ocjenjeno?



}
