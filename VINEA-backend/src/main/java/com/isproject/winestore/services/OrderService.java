package com.isproject.winestore.services;

import com.isproject.winestore.repos.OrderRepoJPA;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepoJPA orderRepoJPA;

    public OrderService(OrderRepoJPA orderRepoJPA) {
        this.orderRepoJPA = orderRepoJPA;
    }
}
