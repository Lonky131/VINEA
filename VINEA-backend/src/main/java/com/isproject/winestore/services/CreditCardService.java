package com.isproject.winestore.services;

import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private final CreditCardService creditCardService;

    public CreditCardService(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }
}
