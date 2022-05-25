package com.isproject.winestore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isproject.winestore.dto.wine.AddWineCategoryDTO;
import com.isproject.winestore.dto.wine.AddWineDTO;
import com.isproject.winestore.dto.wine.PutWineDTO;
import com.isproject.winestore.exceptions.IdNotExistingException;
import com.isproject.winestore.models.Region;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.Winery;
import com.isproject.winestore.services.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc(addFilters = false)
//@SpringBootTest
//@ActiveProfiles("h2")



@WebMvcTest(WineController.class)
public class WineControllerTests {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private WineService wineService;
    @MockBean
    ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String endpoint = "/wine";

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
                new Winery("dobra vinarija", 1987, regions.get(0))
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
    public void simpleFetchingAllWines() throws Exception {
        given(wineService.getWines()).willReturn(wines);

        mvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    public void deleteWineSuccess() throws Exception {
        given(wineService.deleteWine(1)).willReturn(true);

        mvc.perform(delete(endpoint + "/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteWineFail() throws Exception {
        given(wineService.deleteWine(6)).willThrow(IdNotExistingException.class);

        mvc.perform(delete(endpoint + "/6"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getWineByIdSuccess() throws Exception {
        given(wineService.fetchWineInfo(2)).willReturn(wines.get(2));

        mvc.perform(get(endpoint + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(wines.get(2).getName())));
    }

    @Test
    public void getWineByNonExistId() throws Exception {
        given(wineService.fetchWineInfo(6)).willThrow(IdNotExistingException.class);
        mvc.perform(get(endpoint + "/6"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCategoryToWineSuccess() throws Exception {
        given(wineService.addCategoryToWine(1,1, "crno")).willReturn(true);

        mvc.perform(post(endpoint + "/add-category/1")
                        .param("categoryId", "1")
                        .param("value", "crno"))
                .andExpect(status().isCreated());

    }

    @Test
    public void addCategoryToWineFailNoWineId() throws Exception {
        given(wineService.addCategoryToWine(6,1, "crno"))
                .willThrow(new IdNotExistingException("No wine id!"));
        mvc.perform(post(endpoint + "/add-category/6")
                        .param("categoryId", "1")
                        .param("value", "crno"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void addCategoryToWineFailNoCategoryId() throws Exception {
        given(wineService.addCategoryToWine(1,6, "crno"))
                .willThrow(new IdNotExistingException("No category id!"));
        mvc.perform(post(endpoint + "/add-category/1")
                        .param("categoryId", "6")
                        .param("value", "crno"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void putWineSuccess() throws Exception {
        PutWineDTO putWineDTO = new PutWineDTO(1, "ma jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
                "/flowers-7188503__340.jpg", 2);

        given(wineService.updateWine(1, putWineDTO))
                .willReturn(wines.get(5));
        mvc.perform(put(endpoint + "/1")
                        .content(objectMapper.writeValueAsString(putWineDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(putWineDTO.getName())));
    }

    @Test
    public void putWineFailNoWineId() throws Exception {
        PutWineDTO putWineDTO = new PutWineDTO(10, "ma jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
                "/flowers-7188503__340.jpg", 2);

        given(wineService.updateWine(10, putWineDTO))
                .willThrow(new IdNotExistingException("Wine id does not exist!"));
        mvc.perform(put(endpoint + "/10")
                        .content(objectMapper.writeValueAsString(putWineDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void putWineFailNoWineryId() throws Exception {
        PutWineDTO putWineDTO = new PutWineDTO(1, "ma jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
                "/flowers-7188503__340.jpg", 10);

        given(wineService.updateWine(1, putWineDTO))
                .willThrow(IdNotExistingException.class);
        mvc.perform(put(endpoint + "/1")
                        .content(objectMapper.writeValueAsString(putWineDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



//
//    @Test
//    public void addWineSuccessfully() throws Exception {
//        List<Region> regions = Arrays.asList(
//                new Region("dalmacija", "hrvatska"),
//                new Region("slavonija", "hrvatska")
//        );
//        List<Winery> wineries = Arrays.asList(
//                new Winery("dost dobra vinarija", 1999, regions.get(1)),
//                new Winery("jako dobra vinarija", 1967, regions.get(0)),
//                new Winery("dobra vinarija", 1987, regions.get(0))
//        );
//        List<Wine> wines = Arrays.asList(
//                new Wine("dost dobro vino", 2015, 15.5, 1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
//                        "05/18/17/22/leaves-7205773__480.jpg", wineries.get(0)),
//                new Wine("isto jako dobro vino", 2016, 15, 1500, 150, "https://cdn.pixabay.com/photo/2022/05/11/06/00" +
//                        "/flowers-7188503__340.jpg", wineries.get(2)),
//                new Wine("neloše vino", 2010, 13, 1200, 120.50, "https://cdn.pixabay.com/photo/2022/05/11/06/00/f" +
//                        "lowers-7188503__340.jpg", wineries.get(1)),
//                new Wine("solidno vino", 2010, 18, 700, 200, "https://cdn.pixabay.com/photo/2022/05/11/13/55/n" +
//                        "ature-7189418__340.jpg", wineries.get(1)),
//                new Wine("super vino", 1999, 14.5, 700, 300.99, "https://cdn.pixabay.com/photo/2022/04/27/07/47/bee-71" +
//                        "59876__340.jpg", wineries.get(2))
//        );
//
//        AddWineDTO addWineDTO = new AddWineDTO("dost dobro vino", 2015, 15.5, 1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
//                "05/18/17/22/leaves-7205773__480.jpg", 2, new ArrayList<AddWineCategoryDTO>());
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/1").
//                buildAndExpand().toUri();
//
//        ResponseEntity<Object> response = ResponseEntity.created(location).build();
//        given(wineService.addWine(addWineDTO)).willReturn(wines.get(0));
//
//        mvc.perform(post(endpoint)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(addWineDTO)))
//                .andExpect(status().isCreated());
//    }

    @Test
    public void addWineSuccessfully() throws Exception {
        AddWineDTO addWineDTO = new AddWineDTO("dost dobro vino", 2015, 15.5, 1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
                "05/18/17/22/leaves-7205773__480.jpg", 2, new ArrayList<AddWineCategoryDTO>());

        given(wineService.addWine(addWineDTO)).willReturn(wines.get(0));

        mvc.perform(post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addWineDTO)))
                .andExpect(status().isCreated());
    }

}
