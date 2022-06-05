package com.isproject.winestore.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isproject.winestore.TestContainer;
import com.isproject.winestore.dto.wine.*;
import com.isproject.winestore.dto.wineries.AddWineryDTO;
import com.isproject.winestore.dto.wineries.PutWineryDTO;
import com.isproject.winestore.models.*;
import com.isproject.winestore.repos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WinestoreIntegrationApplicationTests extends TestContainer {

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

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

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

	private final String wineEndpoint = "/wine";
	private final String wineryEndpoint = "/winery";
	private final String wineCategoryEndpoint = "/wine-category";

	@BeforeEach
	private void setUp() {

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
	public void getAllWines() throws Exception {
		mockMvc.perform(get(wineEndpoint))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(wines.size())));
	}

	@Test
	public void getWineById() throws Exception {
		mockMvc.perform(get(wineEndpoint + "/" + wines.get(0).getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.wineCategoryDTOList", hasSize(wines.get(0).getCategories().size())));
	}

	@Test
	public void addWineNoWineryId() throws Exception {
		AddWineDTO addWineDTO = new AddWineDTO("dost dobro vino", 2015, 15.5, 1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
				"05/18/17/22/leaves-7205773__480.jpg", -1, new ArrayList<AddWineCategoryDTO>());
		mockMvc.perform(post(wineEndpoint)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(addWineDTO)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addWine() throws Exception {
		AddWineDTO addWineDTO = new AddWineDTO("novo ime vina", 2015, 15.5, 1000, 75.99, "https://cdn.pixabay.com/photo/2022/" +
				"05/18/17/22/leaves-7205773__480.jpg", wineries.get(0).getId(), new ArrayList<AddWineCategoryDTO>());
		mockMvc.perform(post(wineEndpoint)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(addWineDTO)))
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteWine() throws Exception {
		mockMvc.perform(delete(wineEndpoint + "/" + wines.get(0).getId()))
				.andExpect(status().isNoContent());
	}

	@Test
	public void deleteWineNoId() throws Exception {
		mockMvc.perform(delete(wineEndpoint + "/-1"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateWine() throws Exception {
		PutWineDTO putWineDTO = new PutWineDTO(wines.get(1).getId(),
				"novo ime", wines.get(1).getProductionYear(), wines.get(1).getAlcoholPercentage(),
				wines.get(1).getVolume(), wines.get(1).getPrice(), wines.get(1).getPictureUrl(),
				wines.get(1).getWinery().getId());
		mockMvc.perform(put(wineEndpoint + "/" + wines.get(1).getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(putWineDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("novo ime")))
				.andExpect(jsonPath("$.id", is((int) wines.get(1).getId())))
				.andExpect(jsonPath("$.volume", is((int) wines.get(1).getVolume())));
	}

	@Test
	public void updateWineChangeWinery() throws Exception {
		PutWineDTO putWineDTO = new PutWineDTO(wines.get(1).getId(), wines.get(1).getName(),
				wines.get(1).getProductionYear(), wines.get(1).getAlcoholPercentage(), wines.get(1).getVolume(),
				wines.get(1).getPrice(), wines.get(1).getPictureUrl(), wines.get(2).getWinery().getId());
		mockMvc.perform(put(wineEndpoint + "/" + wines.get(1).getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(putWineDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is((int) wines.get(1).getId())))
				.andExpect(jsonPath("$.volume", is((int) wines.get(1).getVolume())))
				.andExpect(jsonPath("$.winery.id", is((int)wines.get(2).getWinery().getId())))
				.andExpect(jsonPath("$.winery.name", is(wines.get(2).getWinery().getName())));
	}

	@Test
	public void getAllWineries() throws Exception {
		mockMvc.perform(get(wineryEndpoint))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(wineries.size())));
	}

	@Test
	public void getWineryById() throws Exception {
		mockMvc.perform(get(wineryEndpoint + "/" + wineries.get(0).getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is((int) wineries.get(0).getId())))
				.andExpect(jsonPath("$.name", is(wineries.get(0).getName())));
	}

	@Test
	public void getWineryByIdNoId() throws Exception {
		mockMvc.perform(get(wineryEndpoint + "/" + -1))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addWinery() throws Exception {
		AddWineryDTO addWineryDTO = new AddWineryDTO("novo ime vinarije", 1987, regions.get(0).getId());
		mockMvc.perform(post(wineryEndpoint)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(addWineryDTO)))
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteWinery() throws Exception {
		mockMvc.perform(delete(wineryEndpoint + "/" + wineries.get(2).getId()))
				.andExpect(status().isNoContent());
		List<Wine> wines1 = wines.stream().filter(wine -> wine.getWinery().getId() == wineries.get(2).getId())
				.collect(Collectors.toList());
		for (Wine wine: wines1) {
			mockMvc.perform(get(wineEndpoint + "/" + wine.getId()))
					.andExpect(status().isBadRequest());
		}
	}

	@Test
	public void updateWinery() throws Exception {
		PutWineryDTO putWineryDTO = new PutWineryDTO(wineries.get(0).getId(),
				"novo ime vinarije", wineries.get(0).getFoundingYear(), regions.get(0).getId());
		mockMvc.perform(put(wineryEndpoint + "/" + putWineryDTO.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(putWineryDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("novo ime vinarije")))
				.andExpect(jsonPath("$.region.id", is((int)regions.get(0).getId())));
	}

	@Test
	public void updateWineryNoRegionId() throws Exception {
		PutWineryDTO putWineryDTO = new PutWineryDTO(wineries.get(0).getId(),
				"novo ime vinarije", wineries.get(0).getFoundingYear(), -1);
		mockMvc.perform(put(wineryEndpoint + "/" + putWineryDTO.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(putWineryDTO)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getAllCategoriesOfWine() throws Exception {
		mockMvc.perform(get(wineCategoryEndpoint + "/" + wines.get(0).getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(wines.get(0).getCategories().size())));
	}

	@Test
	public void addCategoryToWine() throws Exception {
		mockMvc.perform(post(wineCategoryEndpoint + "/" + wines.get(3).getId())
				.queryParam("categoryId", String.valueOf(categories.get(0).getId()))
				.queryParam("value", "bijelo"))
				.andExpect(status().isCreated());
	}

	@Test
	public void addCategoryToWineAlreadyExists() throws Exception {
		mockMvc.perform(post(wineCategoryEndpoint + "/" + wines.get(0).getId())
						.queryParam("categoryId", String.valueOf(categories.get(0).getId()))
						.queryParam("value", "bzvz"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteCategoryFromWine() throws Exception {
		mockMvc.perform(delete(wineCategoryEndpoint + "/" + wines.get(0).getId())
				.queryParam("wineCategoryId", String.valueOf(wineCategories_wine0.get(0).getId())))
				.andExpect(status().isNoContent());
	}

	@Test
	public void deleteCategoryFromWineNoCategory() throws Exception {
		mockMvc.perform(delete(wineCategoryEndpoint + "/" + wines.get(0).getId())
						.queryParam("wineCategoryId", String.valueOf(-1)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateWineCategory() throws Exception {
		mockMvc.perform(put(wineCategoryEndpoint + "/" + wines.get(0).getId())
						.queryParam("wineCategoryId", String.valueOf(wineCategories_wine0.get(0).getId()))
						.queryParam("value", "crno"))
				.andExpect(status().isOk());
	}

	@Test
	public void updateWineCategoryNoWineCategory() throws Exception {
		mockMvc.perform(put(wineCategoryEndpoint + "/" + wines.get(0).getId())
						.queryParam("wineCategoryId", String.valueOf(-1))
						.queryParam("value", "crno"))
				.andExpect(status().isBadRequest());
	}

}
