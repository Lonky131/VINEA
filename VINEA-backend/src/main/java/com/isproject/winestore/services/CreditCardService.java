package com.isproject.winestore.services;

import com.isproject.winestore.repos.CreditCardRepoJPA;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private final CreditCardRepoJPA creditCardRepoJPA;

    public CreditCardService(CreditCardRepoJPA creditCardRepoJPA) {
        this.creditCardRepoJPA = creditCardRepoJPA;
    }
}
