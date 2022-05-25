package com.isproject.winestore.exceptions;

public class DuplicateKeyIdException extends RuntimeException {

    public DuplicateKeyIdException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
