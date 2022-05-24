package com.isproject.winestore.services;

import com.isproject.winestore.repos.ProductRepoJPA;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepoJPA productRepoJPA;

    public ProductService(ProductRepoJPA productRepoJPA) {
        this.productRepoJPA = productRepoJPA;
    }
}
