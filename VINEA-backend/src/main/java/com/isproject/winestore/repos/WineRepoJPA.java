package com.isproject.winestore.repos;

import com.isproject.winestore.models.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepoJPA extends JpaRepository<Wine, Long> {

}
