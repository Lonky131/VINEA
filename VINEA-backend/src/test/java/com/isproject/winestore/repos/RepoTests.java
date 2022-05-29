package com.isproject.winestore.repos;

import com.isproject.winestore.TestContainer;
import com.isproject.winestore.dto.wine.AddWineCategoryDTO;
import com.isproject.winestore.dto.wine.WineCategoryDTO;
import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepoTests extends TestContainer {

    @Autowired
    private WineRepoJPA wineRepoJPA;

    @Autowired
    private WineryRepoJPA wineryRepoJPA;

    @Autowired
    private WineCategoryRepoJPA wineCategoryRepoJPA;

    @Autowired
    private CategoryRepoJPA categoryRepoJPA;

    @Autowired
    private RegionRepoJPA regionRepoJPA;

    List<Region> regions;
    List<Wine> wines;
    List<Winery> wineries;
    List<Category> categories;
    List<WineCategory> wineCategories;
    List<WineCategory> wineCategories_wine0;
    List<WineCategory> wineCategories_wine1;
    List<WineCategoryDTO> wineCategoryDTOS;
    List<WineDTO> wineDTOS;
    List<AddWineCategoryDTO> addWineCategoryDTOS;

    @BeforeEach
    public void setUp() {
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

        wineCategories_wine0 = Arrays.asList(
                new WineCategory(categories.get(0), wines.get(0), "crno"),
                new WineCategory(categories.get(1), wines.get(0), "merlot")
        );

        wineCategories_wine1 = Arrays.asList(
                new WineCategory(categories.get(0), wines.get(1), "bijelo")
        );

        for (int i = 0; i < wineCategories_wine0.size(); i++) {
            wines.get(0).addWineCategory(wineCategories_wine0.get(i));
        }
        for (int i = 0; i < wineCategories_wine1.size(); i++) {
            wines.get(1).addWineCategory(wineCategories_wine1.get(i));
        }

        wineCategoryDTOS = wineCategories.stream().map(wineCategory -> new WineCategoryDTO(
                wineCategory.getId(), wineCategory.getCategory().getId(), wineCategory.getCategory().getName(),
                wineCategory.getValue()
        )).collect(Collectors.toList());

        wineDTOS = wines.stream().map( wine -> new WineDTO(wine)).collect(Collectors.toList());
        addWineCategoryDTOS = wineCategories.stream().map(wineCategory -> new AddWineCategoryDTO(wineCategory))
                .collect(Collectors.toList());

        wineRepoJPA.deleteAll();
        wineryRepoJPA.deleteAll();
        regionRepoJPA.deleteAll();
        categoryRepoJPA.deleteAll();
        wineCategoryRepoJPA.deleteAll();

        categoryRepoJPA.saveAll(categories);
        regionRepoJPA.saveAll(regions);
        wineryRepoJPA.saveAll(wineries);
        wineRepoJPA.saveAll(wines);
        wineCategoryRepoJPA.saveAll(wineCategories_wine0);
        wineCategoryRepoJPA.saveAll(wineCategories_wine1);
    }

    @Test
    public void wineFindById() {
        Optional<Wine> wine = wineRepoJPA.findById(wines.get(0).getId());
        assertThat(wine).isNotEmpty();
    }

    @Test
    public void wineFindByName() {
        Optional<Wine> wine = wineRepoJPA.findByName(wines.get(1).getName());
        assertThat(wine).isNotEmpty();
        assertThat(wine.get().getName()).isEqualTo(wines.get(1).getName());
        assertThat(wine.get().getAlcoholPercentage()).isEqualTo(wines.get(1).getAlcoholPercentage());
    }

    @Test
    public void wineryFindByName() {
        Optional<Winery> winery = wineryRepoJPA.findByName(wineries.get(2).getName());
        assertThat(winery).isNotEmpty();
        assertThat(winery.get().getName()).isEqualTo(wineries.get(2).getName());
        assertThat(winery.get().getFoundingYear()).isEqualTo(wineries.get(2).getFoundingYear());
    }

    @Test
    public void wineCategoryFindByWineAndCategory() {
        Optional<WineCategory> wineCategory = wineCategoryRepoJPA
                .findByCategoryAndWine(wineCategories.get(0).getCategory(), wineCategories.get(0).getWine());
        assertThat(wineCategory.get().getValue()).isEqualTo(wineCategories.get(0).getValue());
        assertThat(wineCategory.get().getCategory().getName()).isEqualTo(wineCategories.get(0).getCategory().getName());
        assertThat(wineCategory.get().getWine().getName()).isEqualTo(wineCategories.get(0).getWine().getName());
    }

    @Test
    public void wineDeleteByWinery() {
        wineRepoJPA.deleteAllByWinery(wineries.get(1));
        assertThat(wineRepoJPA.findAll().size()).isEqualTo(4);
    }

    @Test
    public void wineCategoryFindByWine() {
        List<WineCategory> wineCategory = wineCategoryRepoJPA.findByWine(wines.get(0));
        assertThat(wineCategory.size()).isEqualTo(2);
    }


}
