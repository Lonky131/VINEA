package com.isproject.winestore.repos;

import com.isproject.winestore.models.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineryRepoJPA extends JpaRepository<Winery, Long> {
}
