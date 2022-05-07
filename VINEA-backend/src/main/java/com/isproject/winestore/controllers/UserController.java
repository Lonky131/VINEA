package com.isproject.winestore.controllers;

import com.isproject.winestore.dto.user.UserLoginDTO;
import com.isproject.winestore.dto.user.UserRegisterDTO;
import com.isproject.winestore.services.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/user", consumes = "application/json")
public class UserController {

    private final UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Integer> getUserData(@PathVariable Long userId) {
        // dohvacanje podataka o korisniku, tipa basic podaci ili narudzbe uz to, definirati DTO povratni

        logger.info("Fetching user data...");

        return new ResponseEntity(0, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity loginUser(@RequestBody UserLoginDTO user) {
        //basic login

        logger.info("Logging in...");
        userService.loginUser(user);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody UserRegisterDTO user) {
        //basic registracija korisnika

        logger.info("Registration of new user...");
        userService.registerUser(user);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable long userId) {
        //brisanje korisnika

        logger.info("Deleting user...");
        userService.deleteUser(userId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/")
    public ResponseEntity updateUser(@RequestBody UserLoginDTO user) {
        //updateanje korisnika, vidi da li treba i pathvariable dodat

        logger.info("Updating user...");
        userService.updateUser();

        return new ResponseEntity(HttpStatus.OK);
    }
}
