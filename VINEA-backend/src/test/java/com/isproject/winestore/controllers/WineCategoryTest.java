package com.isproject.winestore.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.isproject.winestore.dto.wine.WineCategoryDTO;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.*;
import com.isproject.winestore.services.WineCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WineCategoryController.class)
public class WineCategoryTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WineCategoryService wineCategoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String endpoint = "/wine-category";

    List<Region> regions;
    List<Wine> wines;
    List<Winery> wineries;
    List<Category> categories;

    List<WineCategory> wineCategories;
    List<WineCategoryDTO> wineCategoryDTOS;

    @BeforeEach
    public void setUp() {
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
    }


    @Test
    public void addCategoryToWineSuccess() throws Exception {
        given(wineCategoryService.addCategoryToWine(1,1, "crno")).willReturn(true);

        mvc.perform(post(endpoint +"/1")
                        .param("categoryId", "1")
                        .param("value", "crno"))
                .andExpect(status().isCreated());

    }

    @Test
    public void addCategoryToWineFailNoWineId() throws Exception {
        given(wineCategoryService.addCategoryToWine(6,1, "crno"))
                .willThrow(new IdNotExistingException("No wine id!"));
        mvc.perform(post(endpoint + "/6")
                        .param("categoryId", "1")
                        .param("value", "crno"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void addCategoryToWineFailNoCategoryId() throws Exception {
        given(wineCategoryService.addCategoryToWine(1,6, "crno"))
                .willThrow(new IdNotExistingException("No category id!"));
        mvc.perform(post(endpoint + "/1")
                        .param("categoryId", "6")
                        .param("value", "crno"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getAllCategoriesOfWine() throws Exception {
        given(wineCategoryService.getWineCategories(1))
                .willReturn(Arrays.asList(wineCategoryDTOS.get(0),wineCategoryDTOS.get(1)));
        mvc.perform(get(endpoint + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].categoryName", is("boja")))
                .andExpect(jsonPath("$[0].value", is("crno")));
    }

    @Test
    public void deleteWineCategorySuccessfully() throws Exception {
        given(wineCategoryService.deleteCategoryFromWine(1, 1))
                .willReturn(true);
        mvc.perform(delete(endpoint + "/1")
                .queryParam("wineCategoryId", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteWineCategoryFailNoWineId() throws Exception {
        given(wineCategoryService.deleteCategoryFromWine(100, 1))
                .willThrow(IdNotExistingException.class);
        mvc.perform(delete(endpoint + "/100")
                        .queryParam("wineCategoryId", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteWineCategoryFailNoWineCategoryId() throws Exception {
        given(wineCategoryService.deleteCategoryFromWine(1, 100))
                .willThrow(IdNotExistingException.class);
            mvc.perform(delete(endpoint + "/1")
                        .queryParam("wineCategoryId", "100"))
                .andExpect(status().isBadRequest());
    }
}
