package com.isproject.winestore.repos;

import com.isproject.winestore.models.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepoJPA extends JpaRepository<Wine, Long> {

}
