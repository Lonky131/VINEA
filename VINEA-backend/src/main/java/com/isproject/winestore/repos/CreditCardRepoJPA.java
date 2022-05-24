package com.isproject.winestore.repos;

import com.isproject.winestore.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepoJPA extends JpaRepository<CreditCard, Long> {
}
