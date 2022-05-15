package com.isproject.winestore.services;

import com.isproject.winestore.dto.wineries.AddWineryDTO;
import com.isproject.winestore.models.Region;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.repos.RegionRepoJPA;
import com.isproject.winestore.repos.WineryRepoJPA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineryService {

    private final WineryRepoJPA wineryRepoJPA;
    private final RegionRepoJPA regionRepoJPA;

    public WineryService(WineryRepoJPA wineryRepoJPA, RegionRepoJPA regionRepoJPA) {
        this.wineryRepoJPA = wineryRepoJPA;
        this.regionRepoJPA = regionRepoJPA;
    }
    
    public Winery getWineryById(long id) {
        return wineryRepoJPA.getById(id);
    }

    public Winery addWinery(AddWineryDTO winery) {
        Region regionEntity = regionRepoJPA.getById(winery.getRegionId());
        return wineryRepoJPA.saveAndFlush(
                new Winery(winery.getName(), winery.getFoundingYear(), regionEntity));
    }

    public List<Winery> getAllWineries() {
        return wineryRepoJPA.findAll();
    }
}
