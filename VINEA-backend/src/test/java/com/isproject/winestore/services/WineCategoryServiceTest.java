package com.isproject.winestore.services;

import com.isproject.winestore.dto.wine.AddWineCategoryDTO;
import com.isproject.winestore.dto.wine.WineCategoryDTO;
import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.exceptions.DuplicateKeyIdException;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.*;
import com.isproject.winestore.repos.CategoryRepoJPA;
import com.isproject.winestore.repos.WineCategoryRepoJPA;
import com.isproject.winestore.repos.WineRepoJPA;
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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class WineCategoryServiceTest {

    @Mock
    private WineRepoJPA wineRepoJPA;
    @Mock
    private CategoryRepoJPA categoryRepoJPA;
    @Mock
    private WineCategoryRepoJPA wineCategoryRepoJPA;

    private WineCategoryService wineCategoryService;

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
        wineCategoryService = new WineCategoryService(wineRepoJPA, categoryRepoJPA, wineCategoryRepoJPA);

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
                new WineCategory(categories.get(0), wines.get(1), "bijelo"),
                new WineCategory(categories.get(0), wines.get(0), "bijelo")
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
    public void addCategoryToWine() {
        given(categoryRepoJPA.findById(1L))
                .willReturn(Optional.ofNullable(categories.get(0)));
        given(wineRepoJPA.findById(1L)).willReturn(Optional.ofNullable(wines.get(0)));
        given(wineCategoryRepoJPA.findByCategoryAndWine(categories.get(0), wines.get(0)))
                .willReturn(Optional.empty());
        WineCategory wineCategory = new WineCategory(categories.get(2), wines.get(0), "dummy");
        given(wineCategoryRepoJPA.saveAndFlush(any(WineCategory.class))).willReturn(wineCategory);
        assertThat(wineCategoryService.addCategoryToWine(1L, 1L, "dummy").getValue())
                .isEqualTo("dummy");
    }

    @Test
    public void addCategoryToWineNoCategoryId() {
        given(categoryRepoJPA.findById(100L))
                .willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineCategoryService.addCategoryToWine(1L, 100L, "dummy"));
    }

    @Test
    public void addCategoryToWineNoWineId() {
        given(categoryRepoJPA.findById(1L))
                .willReturn(Optional.ofNullable(categories.get(0)));
        given(wineRepoJPA.findById(100L)).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineCategoryService.addCategoryToWine(100L, 1L, "dummy"));
    }

    @Test
    public void addCategoryWineWineCategoryAlreadyExists() {
        given(categoryRepoJPA.findById(1L))
                .willReturn(Optional.ofNullable(categories.get(0)));
        given(wineRepoJPA.findById(1L)).willReturn(Optional.ofNullable(wines.get(0)));
        given(wineCategoryRepoJPA.findByCategoryAndWine(categories.get(0), wines.get(0)))
                .willReturn(Optional.of(wineCategories.get(0)));
        assertThrows(DuplicateKeyIdException.class, () -> wineCategoryService.addCategoryToWine(1L, 1L, "dummy"));
    }

    @Test
    public void getWineCategories() {
        given(wineRepoJPA.findById(1L)).willReturn(Optional.ofNullable(wines.get(0)));
        given(wineCategoryRepoJPA.findByWine(wines.get(0)))
                .willReturn(wineCategories);
        assertThat(wineCategoryService.getWineCategories(1L)).hasSize(wineCategories.size());
        for (int i = 0; i < wineCategoryDTOS.size(); i++) {
            assertThat(wineCategoryService.getWineCategories(1L).get(i).getValue())
                    .isEqualTo(wineCategoryDTOS.get(i).getValue());
        }
    }

    @Test
    public void getWineCategoriesNoWineId() {
        given(wineRepoJPA.findById(100L)).willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class, () -> wineCategoryService.getWineCategories(100L));
    }

    @Test
    public void deleteCategoryFromWine() {
        Wine wine = new Wine( "dost dobro vino", 2015, 15.5, 1000,
                75.99, "https://cdn.pixabay.com/photo/2022/" +
                "05/18/17/22/leaves-7205773__480.jpg", wineries.get(0));
        wine.setId(1L);
        WineCategory wineCategory = new WineCategory(categories.get(0), wine, "crno");
        given(wineCategoryRepoJPA.findById(1L))
                .willReturn(Optional.ofNullable(wineCategory));
        assertDoesNotThrow(() -> wineCategoryService.deleteCategoryFromWine(1L, 1L));
    }


    @Test
    public void deleteWineCategoryNoWineCategory() {
        given(wineCategoryRepoJPA.findById(100L))
                .willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class,
                () -> wineCategoryService.deleteCategoryFromWine(1L, 100L));
    }

    @Test
    public void deleteCategoryFromWineWineHasNoCategory() {
        Wine wine = new Wine( "dost dobro vino", 2015, 15.5, 1000,
                75.99, "https://cdn.pixabay.com/photo/2022/" +
                "05/18/17/22/leaves-7205773__480.jpg", wineries.get(0));
        wine.setId(2L);
        WineCategory wineCategory = new WineCategory(categories.get(0), wine, "crno");
        given(wineCategoryRepoJPA.findById(1L))
                .willReturn(Optional.ofNullable(wineCategory));
        assertThrows(IdNotExistingException.class, () -> wineCategoryService.deleteCategoryFromWine(1L, 1L));
    }

    @Test
    public void updateWineCategory() {
        given(wineRepoJPA.findById(1L))
                .willReturn(Optional.ofNullable(wines.get(0)));
        given(wineCategoryRepoJPA.findById(10L))
                .willReturn(Optional.ofNullable(wineCategories.get(0)));
        given(wineCategoryRepoJPA.saveAndFlush(any(WineCategory.class)))
                .willReturn(wineCategories.get(3));
        assertThat(wineCategoryService.updateWineCategory(1L,10L, "bijelo"));

    }

    @Test
    public void updateWineCategoryNoWineId() {
        given(wineRepoJPA.findById(1L))
                .willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class,
                () -> wineCategoryService.updateWineCategory(1L,10L, "bijelo"));
    }

    @Test
    public void updateWineCategoryNoWineCategoryId() {
        given(wineRepoJPA.findById(1L))
                .willReturn(Optional.ofNullable(wines.get(0)));
        given(wineCategoryRepoJPA.findById(10L))
                .willThrow(IdNotExistingException.class);
        assertThrows(IdNotExistingException.class,
                () -> wineCategoryService.updateWineCategory(1L,10L, "bijelo"));

    }
}

