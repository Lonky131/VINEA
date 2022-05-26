package com.isproject.winestore.controllers;

import com.isproject.winestore.dto.wineries.AddWineryDTO;
import com.isproject.winestore.dto.wineries.PutWineryDTO;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.services.WineryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/winery")
public class WineryController {

    private final WineryService wineryService;
    private final static Logger logger = LoggerFactory.getLogger(WineryController.class);

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Winery> getWinery(@PathVariable long id) {
        logger.info("Fetching winery with id " + id + "...");
        return new ResponseEntity<Winery>(wineryService.getWineryById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Winery>> getAllWineries() {
        logger.info("Fetching all wineries...");
        return new ResponseEntity<List<Winery>>(wineryService.getAllWineries(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addWinery(@RequestBody AddWineryDTO winery) {
        logger.info("Adding new winery...");
        Winery winery1 = wineryService.addWinery(winery);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/" + winery1.getId())
//                .buildAndExpand()
//                .toUri();
        return new ResponseEntity(winery1, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{wineryId}")
    public ResponseEntity<Object> deleteWinery(@PathVariable long wineryId) {
        logger.info("Deleting winery " + wineryId + "...");
        wineryService.deleteWinery(wineryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{wineryId}")
    public ResponseEntity<Winery> updateWine(@PathVariable long wineryId, @RequestBody PutWineryDTO wineryDTO) {
        logger.info("Updating winery with id " + wineryId + "...");
        return new ResponseEntity<Winery>(wineryService.updateWinery(wineryId, wineryDTO), HttpStatus.OK);
    }

    @ExceptionHandler(value = {IdNotExistingException.class})
    public ResponseEntity<String> handleException(RuntimeException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
