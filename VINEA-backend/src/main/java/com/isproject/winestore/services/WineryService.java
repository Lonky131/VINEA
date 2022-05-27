package com.isproject.winestore.services;

import com.isproject.winestore.dto.wineries.AddWineryDTO;
import com.isproject.winestore.dto.wineries.PutWineryDTO;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Region;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.repos.RegionRepoJPA;
import com.isproject.winestore.repos.WineRepoJPA;
import com.isproject.winestore.repos.WineryRepoJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WineryService {

    private final WineryRepoJPA wineryRepoJPA;
    private final RegionRepoJPA regionRepoJPA;
    private final WineRepoJPA wineRepoJPA;

    public WineryService(WineryRepoJPA wineryRepoJPA, RegionRepoJPA regionRepoJPA, WineRepoJPA wineRepoJPA) {
        this.wineryRepoJPA = wineryRepoJPA;
        this.regionRepoJPA = regionRepoJPA;
        this.wineRepoJPA = wineRepoJPA;
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

    @Transactional
    public boolean deleteWinery(long wineryId) {
        Optional<Winery> winery = wineryRepoJPA.findById(wineryId);
        if (winery.isEmpty())
            throw new IdNotExistingException("Winery id does not exist!");
        wineRepoJPA.deleteAllByWinery(winery.get());
        wineryRepoJPA.deleteById(wineryId);
        return true;
    }

    public Winery updateWinery(long wineryId, PutWineryDTO wineryDTO) {
        Optional<Winery> wineryEntity = wineryRepoJPA.findById(wineryId);
        if (wineryEntity.isEmpty()) {
            throw new IdNotExistingException("Winery id does not exist!");
        }
        Winery winery = wineryEntity.get();
        winery.setName(wineryDTO.getName());
        winery.setFoundingYear(wineryDTO.getFoundingYear());
        Optional<Region> region = regionRepoJPA.findById(wineryDTO.getRegionId());
        if (region.isEmpty()) {
            throw new IdNotExistingException("Region id does not exist!");
        }
        winery.setRegion(region.get());
        return wineryRepoJPA.saveAndFlush(winery);
    }
}
