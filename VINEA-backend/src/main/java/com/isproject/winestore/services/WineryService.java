package com.isproject.winestore.services;

import com.isproject.winestore.dto.wineries.AddWineryDTO;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Region;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.repos.RegionRepoJPA;
import com.isproject.winestore.repos.WineryRepoJPA;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineryService {

    private final WineryRepoJPA wineryRepoJPA;
    private final RegionRepoJPA regionRepoJPA;

    public WineryService(WineryRepoJPA wineryRepoJPA, RegionRepoJPA regionRepoJPA) {
        this.wineryRepoJPA = wineryRepoJPA;
        this.regionRepoJPA = regionRepoJPA;
    }
    
    public Winery getWineryById(long id) {
        Optional<Winery> winery = wineryRepoJPA.findById(id);
        if (winery.isEmpty())
            throw new IdNotExistingException("Winery id does not exist!");
        return winery.get();
    }

    public Winery addWinery(AddWineryDTO winery) {
        Optional<Region> regionEntity = regionRepoJPA.findById(winery.getRegionId());
        if (regionEntity.isEmpty())
            throw new IdNotExistingException("Winery id does not exist!");
        return wineryRepoJPA.saveAndFlush(
                new Winery(winery.getName(), winery.getFoundingYear(), regionEntity.get()));
    }

    public List<Winery> getAllWineries() {
        return wineryRepoJPA.findAll();
    }
}
