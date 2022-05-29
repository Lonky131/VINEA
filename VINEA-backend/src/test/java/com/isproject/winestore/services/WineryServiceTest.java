package com.isproject.winestore.services;

import com.isproject.winestore.dto.wine.AddWineCategoryDTO;
import com.isproject.winestore.dto.wine.WineCategoryDTO;
import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.dto.wineries.AddWineryDTO;
import com.isproject.winestore.dto.wineries.PutWineryDTO;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.exceptions.NameAlreadyExistsException;
import com.isproject.winestore.exceptions.YearNotValidException;
import com.isproject.winestore.models.*;
import com.isproject.winestore.repos.RegionRepoJPA;
import com.isproject.winestore.repos.WineRepoJPA;
import com.isproject.winestore.repos.WineryRepoJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class WineryServiceTest {

    @Mock
    private WineryRepoJPA wineryRepoJPA;

    @Mock
    private RegionRepoJPA regionRepoJPA;

    @Mock
    private WineRepoJPA wineRepoJPA;
    private WineryService wineryService;

    List<Region> regions;
    List<Wine> wines;
    List<Winery> wineries;
    List<Category> categories;
    List<WineCategory> wineCategories;
    List<WineCategoryDTO> wineCategoryDTOS;
    List<WineDTO> wineDTOS;
    List<AddWineCategoryDTO> addWineCategoryDTOS;

    @BeforeEach
    public void setUp() {
        wineryService = new WineryService(wineryRepoJPA, regionRepoJPA, wineRepoJPA);

        regions = Arrays.asList(
                new Region("dalmacija", "hrvatska"),
                new Region("slavonija", "hrvatska")
        );
        wineries = Arrays.asList(
                new Winery("dost dobra vinarija", 1999, regions.get(1)),
                new Winery("jako dobra vinarija", 1967, regions.get(0)),
                new Winery("dobra vinarija", 1987, regions.get(0)),
                new Winery("dobra vinarija", 1988, regions.get(0))
        );
        categories = Arrays.asList(
                new Category("boja"),
                new Category("sorta"),
                new Category("sladilo")
        );
        wines = Arrays.asList(
                new Wine("dost dobro vino", 2015, 15.5, 1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
                        "05/18/17/22/leaves-7205773__480.jpg", wineries.get(0)),
                new Wine("isto jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
                        "/flowers-7188503__340.jpg", wineries.get(2)),
                new Wine("neloše vino", 2010, 13, 1200, 120.50, "https://cdn.pixabay.com/photo/2022/05/11/06/00/f" +
                        "lowers-7188503__340.jpg", wineries.get(1)),
                new Wine("solidno vino", 2010, 18, 700, 200, "https://cdn.pixabay.com/photo/2022/05/11/13/55/n" +
                        "ature-7189418__340.jpg", wineries.get(1)),
                new Wine("super vino", 1999, 14.5, 700, 300.99, "https://cdn.pixabay.com/photo/2022/04/27/07/47/bee-71" +
                        "59876__340.jpg", wineries.get(2)),
                new Wine("ma jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
                        "/flowers-7188503__340.jpg", wineries.get(2))
        );

        wineCategories = Arrays.asList(
                new WineCategory(categories.get(0), wines.get(0), "crno"),
                new WineCategory(categories.get(1), wines.get(0), "merlot"),
                new WineCategory(categories.get(0), wines.get(1), "bijelo")
        );

        wineCategoryDTOS = wineCategories.stream().map(wineCategory -> new WineCategoryDTO(
                wineCategory.getId(), wineCategory.getCategory().getId(), wineCategory.getCategory().getName(),
                wineCategory.getValue()
        )).collect(Collectors.toList());

        wineDTOS = wines.stream().map(wine -> new WineDTO(wine)).collect(Collectors.toList());
        addWineCategoryDTOS = wineCategories.stream().map(wineCategory -> new AddWineCategoryDTO(wineCategory))
                .collect(Collectors.toList());

    }


    @Test
    public void getWineryById() {
        given(wineryRepoJPA.findById(2L)).willReturn(Optional.ofNullable(wineries.get(1)));
        assertThat(wineryService.getWineryById(2L).getName()).isEqualTo(wineries.get(1).getName());
    }

    @Test
    public void getWineryByIdFail() {
        given(wineryRepoJPA.findById(20L)).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineryService.getWineryById(20L));
    }

    @Test
    public void getAllWineries() {
        given(wineryRepoJPA.findAll()).willReturn(wineries);
        assertThat(wineryService.getAllWineries()).hasSize(wineries.size());
    }

    @Test
    public void addWinery() {
        AddWineryDTO addWineryDTO = new AddWineryDTO("dobra vinarija", 1987, 1);
        given(regionRepoJPA.findById(addWineryDTO.getRegionId())).willReturn(Optional.ofNullable(regions.get(0)));
        given(wineryRepoJPA.saveAndFlush(any(Winery.class))).willReturn(wineries.get(2));
        assertThat(wineryService.addWinery(addWineryDTO).getName()).isEqualTo(wineries.get(2).getName());
    }

    @Test
    public void addWineryNoRegionId() {
        AddWineryDTO addWineryDTO = new AddWineryDTO("dobra vinarija", 1987, 100);
        given(regionRepoJPA.findById(addWineryDTO.getRegionId())).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineryService.addWinery(addWineryDTO));
    }

    @Test
    public void addWineryNameExisting() {
        AddWineryDTO addWineryDTO = new AddWineryDTO(wines.get(0).getName(), 1987, 100);
        given(regionRepoJPA.findById(addWineryDTO.getRegionId())).willThrow(NameAlreadyExistsException.class);
        assertThrows(NameAlreadyExistsException.class, () -> wineryService.addWinery(addWineryDTO));
    }

    @Test
    public void deleteWinery() {
        given(wineryRepoJPA.findById(1L)).willReturn(Optional.ofNullable(wineries.get(0)));
        assertThat(wineryService.deleteWinery(1L)).isTrue();
    }

    @Test
    public void deleteWineryNoIdFail() {
        given(wineryRepoJPA.findById(100L)).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineryService.deleteWinery(100L));
    }

    @Test
    public void updateWinery() {
        PutWineryDTO putWineryDTO = new PutWineryDTO(3,"dobra vinarija", 1988, 1);
        given(wineryRepoJPA.findById(putWineryDTO.getId()))
                .willReturn(Optional.ofNullable(wineries.get((int) putWineryDTO.getId())));
        given(regionRepoJPA.findById(putWineryDTO.getRegionId()))
                .willReturn(Optional.ofNullable(regions.get((int) putWineryDTO.getRegionId())));
        given(wineryRepoJPA.saveAndFlush(any(Winery.class))).willReturn(wineries.get(3));
        assertThat(wineryService.updateWinery(putWineryDTO.getId(), putWineryDTO).getFoundingYear())
                .isEqualTo(wineries.get(3).getFoundingYear());
    }

    @Test
    public void updateWineryNoWineryId() {
        PutWineryDTO putWineryDTO = new PutWineryDTO(100,"dobra vinarija", 1988, 1);
        given(wineryRepoJPA.findById(putWineryDTO.getId())).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineryService.updateWinery(putWineryDTO.getId(), putWineryDTO));
    }

    @Test
    public void updateWineryInvalidYear() {
        PutWineryDTO putWineryDTO = new PutWineryDTO(100,"dobra vinarija", 2500, 1);
        given(wineryRepoJPA.findById(putWineryDTO.getId())).willThrow(YearNotValidException.class);
        assertThrows(YearNotValidException.class, () -> wineryService.updateWinery(putWineryDTO.getId(), putWineryDTO));
    }

    @Test
    public void updateWineryNoRegionId() {
        PutWineryDTO putWineryDTO = new PutWineryDTO(1,"dobra vinarija", 1988, 100);
        given(wineryRepoJPA.findById(putWineryDTO.getId()))
                .willReturn(Optional.ofNullable(wineries.get((int) putWineryDTO.getId())));
        given(regionRepoJPA.findById(putWineryDTO.getRegionId())).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineryService.updateWinery(putWineryDTO.getId(), putWineryDTO));
    }


}
