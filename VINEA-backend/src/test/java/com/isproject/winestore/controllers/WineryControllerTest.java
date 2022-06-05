package com.isproject.winestore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Region;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.services.WineryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class WineryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WineryService wineryService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String endpoint = "/winery";

    List<Region> regions;
    List<Wine> wines;
    List<Winery> wineries;

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
    }

    @Test
    public void simpleFetchingAllWineries() throws Exception {
        given(wineryService.getAllWineries()).willReturn(wineries);

        mvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void deleteWinerySuccess() throws Exception {
        given(wineryService.deleteWinery(1)).willReturn(true);

        mvc.perform(delete(endpoint + "/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteWineryFail() throws Exception {
        given(wineryService.deleteWinery(6)).willThrow(IdNotExistingException.class);

        mvc.perform(delete(endpoint + "/6"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getWineryByIdSuccess() throws Exception {
        given(wineryService.getWineryById(2)).willReturn(wineries.get(2));

        mvc.perform(get(endpoint + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(wineries.get(2).getName())));
    }

    @Test
    public void getWineryByNonExistId() throws Exception {
        given(wineryService.getWineryById(6)).willThrow(IdNotExistingException.class);
        mvc.perform(get(endpoint + "/6"))
                .andExpect(status().isBadRequest());
    }

}
