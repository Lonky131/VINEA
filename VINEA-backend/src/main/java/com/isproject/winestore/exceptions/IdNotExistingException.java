package com.isproject.winestore.exceptions;

public class IdNotExistingException extends RuntimeException {

    private String message;

    public IdNotExistingException(String message) {
        super(message);
    }
}

