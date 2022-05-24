package com.isproject.winestore.repos;

import com.isproject.winestore.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepoJPA extends JpaRepository<Region, Long> {
}
