package com.isproject.winestore.services;


import com.isproject.winestore.dto.wine.*;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.exceptions.YearNotValidException;
import com.isproject.winestore.models.*;
import com.isproject.winestore.repos.CategoryRepoJPA;
import com.isproject.winestore.repos.WineCategoryRepoJPA;
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
public class WineServiceTest {

    @Mock
    private WineRepoJPA wineRepoJPA;
    @Mock
    private WineryRepoJPA wineryRepoJPA;
    @Mock
    private CategoryRepoJPA categoryRepoJPA;
    @Mock
    private WineCategoryRepoJPA wineCategoryRepoJPA;
    @Mock
    private WineCategoryService wineCategoryService;

    private WineService wineService;

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
        wineService = new WineService(wineRepoJPA, wineryRepoJPA, categoryRepoJPA,
                wineCategoryRepoJPA, wineCategoryService);

        regions = Arrays.asList(
                new Region("dalmacija", "hrvatska"),
                new Region("slavonija", "hrvatska")
        );
        wineries = Arrays.asList(
                new Winery("dost dobra vinarija", 1999, regions.get(1)),
                new Winery("jako dobra vinarija", 1967, regions.get(0)),
                new Winery("dobra vinarija", 1987, regions.get(0))
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

        wineDTOS = wines.stream().map( wine -> new WineDTO(wine)).collect(Collectors.toList());
        addWineCategoryDTOS = wineCategories.stream().map(wineCategory -> new AddWineCategoryDTO(wineCategory))
                .collect(Collectors.toList());

    }

    @Test
    public void getAllWInes() {
        given(wineRepoJPA.findAll()).willReturn(wines);
        assertThat(wineService.getWines()).hasSize(6);
        assertThat(wineService.getWines()).isEqualTo(wines);
    }

    @Test
    public void getWineById() {
        given(wineRepoJPA.findById(1L)).willReturn(Optional.of(wines.get(0)));
        given(wineCategoryService.getWineCategories(1L)).willReturn(
                Arrays.asList(wineCategoryDTOS.get(0), wineCategoryDTOS.get(1))
        );
        assertThat(wineService.fetchWineInfo(1L).getName()).isEqualTo(wines.get(0).getName());
        assertThat(wineService.fetchWineInfo(1L).getVolume()).isEqualTo(wines.get(0).getVolume());
        assertThat(wineService.fetchWineInfo(1L).getWineCategoryDTOList()).hasSize(2);
        assertThat(wineService.fetchWineInfo(1L).getWineCategoryDTOList().get(0).getCategoryName())
                .isEqualTo(wineCategoryDTOS.get(0).getCategoryName());
        assertThat(wineService.fetchWineInfo(1L).getWineCategoryDTOList().get(0).getValue())
                .isEqualTo(wineCategoryDTOS.get(0).getValue());
    }

    @Test
    public void getWineByIdFail() {
        given(wineRepoJPA.findById(100L)).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineService.fetchWineInfo(100L));
    }

    @Test
    public void addWine() {
        List<AddWineCategoryDTO> categoryDTOS = Arrays.asList(addWineCategoryDTOS.get(0),
                addWineCategoryDTOS.get(1));
        AddWineDTO addWineDTO = new AddWineDTO("dost dobro vino", 2015, 15.5,
                1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
                "05/18/17/22/leaves-7205773__480.jpg", 1, categoryDTOS);
        given(wineryRepoJPA.findById(addWineDTO.getWineryId())).willReturn(
                Optional.of(wineries.get((int) (addWineDTO.getWineryId() - 1))));
        for (int i = 0; i < categoryDTOS.size(); i++) {
            given(categoryRepoJPA.findById(categoryDTOS.get(i).getCategoryId()))
                    .willReturn(Optional.of(categories.get(i)));
        }
        given(wineRepoJPA.saveAndFlush(any(Wine.class))).willReturn(wines.get(0));
        assertThat(wineService.addWine(addWineDTO).getName()).isEqualTo(wines.get(0).getName());
        assertThat(wineService.addWine(addWineDTO).getWinery().getName()).isEqualTo(
                wines.get(0).getWinery().getName()
        );
    }



    @Test
    public void addWineNoCategoryFail() {
        List<AddWineCategoryDTO> categoryDTOS = Arrays.asList(addWineCategoryDTOS.get(0),
                addWineCategoryDTOS.get(1), new AddWineCategoryDTO(33L, "dummy value"));
        AddWineDTO addWineDTO = new AddWineDTO("dost dobro vino", 2015, 15.5,
                1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
                "05/18/17/22/leaves-7205773__480.jpg", 1, categoryDTOS);
        given(wineryRepoJPA.findById(addWineDTO.getWineryId())).willReturn(
                Optional.of(wineries.get((int) (addWineDTO.getWineryId() - 1))));
        for (int i = 0; i < categoryDTOS.size() - 1; i++) {
            given(categoryRepoJPA.findById(categoryDTOS.get(i).getCategoryId()))
                    .willReturn(Optional.of(categories.get(i)));
        }
        given(categoryRepoJPA.findById(33L)).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineService.addWine(addWineDTO));
    }

    @Test
    public void addWineNoWineryFail() {
        List<AddWineCategoryDTO> categoryDTOS = Arrays.asList(addWineCategoryDTOS.get(0),
                addWineCategoryDTOS.get(1));
        AddWineDTO addWineDTO = new AddWineDTO("dost dobro vino", 2015, 15.5,
                1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
                "05/18/17/22/leaves-7205773__480.jpg", 1, categoryDTOS);
        given(wineryRepoJPA.findById(addWineDTO.getWineryId())).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineService.addWine(addWineDTO));
    }

    @Test
    public void addWineInvalidYear() {
        List<AddWineCategoryDTO> categoryDTOS = Arrays.asList(addWineCategoryDTOS.get(0),
                addWineCategoryDTOS.get(1));
        AddWineDTO addWineDTO = new AddWineDTO("dost dobro vino", 3000, 15.5,
                1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
                "05/18/17/22/leaves-7205773__480.jpg", 1, categoryDTOS);
        given(wineryRepoJPA.findById(addWineDTO.getWineryId())).willThrow(YearNotValidException.class);
        assertThrows(YearNotValidException.class, () -> wineService.addWine(addWineDTO));
    }

    @Test
    public void deleteWine() {
        given(wineRepoJPA.findById(1L)).willReturn(Optional.ofNullable(wines.get(0)));
        assertThat(wineService.deleteWine(1L)).isTrue();
    }

    @Test
    public void deleteWineFailNoId() {
        given(wineRepoJPA.findById(100L)).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineService.deleteWine(100L));
    }

    @Test
    public void updateWine() {
        PutWineDTO putWineDTO = new PutWineDTO(2, "ma jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
                "/flowers-7188503__340.jpg", 3);
        given(wineRepoJPA.findById(putWineDTO.getId())).willReturn(Optional.ofNullable(wines.get(
                (int) (putWineDTO.getId() - 1))));
        given(wineryRepoJPA.findById(putWineDTO.getWineryId())).willReturn(Optional.ofNullable(
                wineries.get((int) (putWineDTO.getWineryId() - 1))));
        given(wineRepoJPA.saveAndFlush(any(Wine.class))).willReturn(wines.get(5));
        System.out.println(wines.get(5).getName());
        assertThat(wineService.updateWine(putWineDTO.getId(), putWineDTO).getName()).isEqualTo(putWineDTO.getName());
    }

    @Test
    public void updateWineNoWineId() {
        PutWineDTO putWineDTO = new PutWineDTO(2, "ma jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
                "/flowers-7188503__340.jpg", 3);
        given(wineRepoJPA.findById(putWineDTO.getId())).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineService.updateWine(putWineDTO.getId(), putWineDTO));
    }

    @Test
    public void updateWineNoWineryId() {
        PutWineDTO putWineDTO = new PutWineDTO(2, "ma jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
                "/flowers-7188503__340.jpg", 3);
        given(wineRepoJPA.findById(putWineDTO.getId())).willReturn(Optional.ofNullable(wines.get(
                (int) (putWineDTO.getId() - 1))));
        given(wineryRepoJPA.findById(putWineDTO.getWineryId())).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineService.updateWine(putWineDTO.getId(), putWineDTO));
    }


}
