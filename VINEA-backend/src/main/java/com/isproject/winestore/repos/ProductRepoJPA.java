package com.isproject.winestore.repos;

import com.isproject.winestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepoJPA extends JpaRepository<Product, Long> {
}
