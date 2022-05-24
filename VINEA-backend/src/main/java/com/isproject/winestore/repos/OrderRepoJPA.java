package com.isproject.winestore.repos;

import com.isproject.winestore.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepoJPA extends JpaRepository<Order, Long> {
}
