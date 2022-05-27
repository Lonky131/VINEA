package com.isproject.winestore.controllers;

import com.isproject.winestore.dto.wine.AddWineDTO;
import com.isproject.winestore.dto.wine.PutWineDTO;
import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.exceptions.DuplicateKeyIdException;
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

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/wine")
public class WineController {

    private final WineService wineService;
    private final static Logger logger = LoggerFactory.getLogger(WineController.class);

    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    public ResponseEntity<List<WineDTO>> getAllWines() {
        logger.info("Fetching all wines...");
        return new ResponseEntity<List<WineDTO>>(wineService.getWines()
                .stream().map(wine -> new WineDTO(wine)).collect(Collectors.toList()),
                HttpStatus.OK);
    }


    @GetMapping(value = "/{wineId}")
    public ResponseEntity<WineDTO> getWine(@PathVariable Long wineId) {
        //info o proizvodu
        logger.info("Fetching info about wine...");
        wineService.fetchWineInfo(wineId);

        return new ResponseEntity(wineService.fetchWineInfo(wineId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Wine> addWine(@RequestBody AddWineDTO wine) {
        //dodat vino
        logger.info("Adding wine...");
        Wine wineEntity = wineService.addWine(wine);
        return new ResponseEntity(wineEntity, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{wineId}")
    public ResponseEntity deleteWine(@PathVariable long wineId) {
        //brisanje vina
        logger.info("Deleting wine " + wineId + "...");
        wineService.deleteWine(wineId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WineDTO> updateWine(@PathVariable long id, @RequestBody PutWineDTO wine) {
        logger.info("Updating wine with id " + id + "...");
        return new ResponseEntity<WineDTO>(new WineDTO(wineService.updateWine(id, wine)), HttpStatus.OK);
    }

    @ExceptionHandler(value = {IdNotExistingException.class, DuplicateKeyIdException.class})
    public ResponseEntity<String> handleException(RuntimeException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
