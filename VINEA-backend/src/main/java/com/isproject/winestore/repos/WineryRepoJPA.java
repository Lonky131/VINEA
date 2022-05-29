package com.isproject.winestore.repos;

import com.isproject.winestore.models.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WineryRepoJPA extends JpaRepository<Winery, Long> {

    Optional<Winery> findByName(String name);
}
