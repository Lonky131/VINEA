package com.isproject.winestore.repos;

import com.isproject.winestore.dto.category.CategoryDTO;
import com.isproject.winestore.dto.region.RegionDTO;
import com.isproject.winestore.dto.wine.WineDTO;
import com.isproject.winestore.dto.wineries.WineryDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WineRepoJdbc {

    private final JdbcTemplate jdbcTemplate;

    public WineRepoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WineDTO> getWines() {

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SELECT * FROM wines"
        );

        List<WineDTO> wines = new ArrayList<>();

        for (Map row : rows) {
            long id = Long.parseLong(row.get("id").toString());
            String name = row.get("name").toString();
            int productionYear = Integer.parseInt(row.get("production_year").toString());
            double alcoholPercentage = Double.parseDouble(row.get("alcohol_percentage").toString());
            int volume = Integer.parseInt(row.get("volume").toString());
            double price = Double.parseDouble(row.get("price").toString());
            String pictureUrl = row.get("picture_url").toString();
            long wineryId = Long.parseLong(row.get("winery_id").toString());
            WineryDTO winery = jdbcTemplate.queryForObject("SELECT * FROM wineries WHERE id = ?",
                    new Object[] {wineryId},
                    (rs, rowNum) ->
                        new WineryDTO(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("founding_year"),
                                jdbcTemplate.queryForObject("SELECT * FROM regions WHERE id = ?",
                                        new Object[] {rs.getLong("regionId")},
                                        (rs2, rowNum2) ->
                                                new RegionDTO(
                                                        rs2.getLong("id"), rs2.getString("name"),
                                                        rs2.getString("country")
                                                )
                                )
                        )
                    );
            WineDTO wine = new WineDTO(id, name, productionYear,alcoholPercentage, volume, price, pictureUrl, winery);
            wines.add(wine);
        }

        return wines;
    }

    public List<WineDTO> getWinesByName(String name) {

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SELECT * FROM wines WHERE LOWER(name) LIKE CONCAT('%', ?, '%')",
                new Object[] {name.toLowerCase()}
        );

        List<WineDTO> wines = new ArrayList<>();

        for (Map row : rows) {
            long id = Long.parseLong(row.get("id").toString());
            String wineName = row.get("name").toString();
            int productionYear = Integer.parseInt(row.get("production_year").toString());
            double alcoholPercentage = Double.parseDouble(row.get("alcohol_percentage").toString());
            int volume = Integer.parseInt(row.get("volume").toString());
            double price = Double.parseDouble(row.get("price").toString());
            String pictureUrl = row.get("picture_url").toString();
            long wineryId = Long.parseLong(row.get("winery_id").toString());
            WineryDTO winery = jdbcTemplate.queryForObject("SELECT * FROM wineries WHERE id = ?",
                    new Object[] {wineryId},
                    (rs, rowNum) ->
                            new WineryDTO(
                                    rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getInt("founding_year"),
                                    jdbcTemplate.queryForObject("SELECT * FROM regions WHERE id = ?",
                                            new Object[] {rs.getLong("regionId")},
                                            (rs2, rowNum2) ->
                                                    new RegionDTO(
                                                            rs2.getLong("id"), rs2.getString("name"),
                                                            rs2.getString("country")
                                                    )
                                    )
                            )
            );
            WineDTO wine = new WineDTO(id, wineName, productionYear,alcoholPercentage, volume, price, pictureUrl, winery);
            wines.add(wine);
        }

        return wines;
    }

    public List<WineDTO> getWinesByColor(String color) {

        CategoryDTO category = jdbcTemplate.queryForObject("SELECT * FROM categories WHERE name = 'boja'",
                (rs, rowNum) ->
                        new CategoryDTO(
                                rs.getLong("id"),
                                rs.getString("name")
                        )
        );

        long categoryId = category.getId();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SELECT * FROM wines NATURAL JOIN has_category_value " +
                        "WHERE has_category_value.category_id = ? AND value = ?",
                new Object[] {categoryId, color}
        );

        List<WineDTO> wines = new ArrayList<>();

        for (Map row : rows) {
            long id = Long.parseLong(row.get("id").toString());
            String wineName = row.get("name").toString();
            int productionYear = Integer.parseInt(row.get("production_year").toString());
            double alcoholPercentage = Double.parseDouble(row.get("alcohol_percentage").toString());
            int volume = Integer.parseInt(row.get("volume").toString());
            double price = Double.parseDouble(row.get("price").toString());
            String pictureUrl = row.get("picture_url").toString();
            long wineryId = Long.parseLong(row.get("winery_id").toString());
            WineryDTO winery = jdbcTemplate.queryForObject("SELECT * FROM wineries WHERE id = ?",
                    new Object[] {wineryId},
                    (rs, rowNum) ->
                            new WineryDTO(
                                    rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getInt("founding_year"),
                                    jdbcTemplate.queryForObject("SELECT * FROM regions WHERE id = ?",
                                            new Object[] {rs.getLong("regionId")},
                                            (rs2, rowNum2) ->
                                                    new RegionDTO(
                                                            rs2.getLong("id"), rs2.getString("name"),
                                                            rs2.getString("country")
                                                    )
                                    )
                            )
            );
            WineDTO wine = new WineDTO(id, wineName, productionYear,alcoholPercentage, volume, price, pictureUrl, winery);
            wines.add(wine);
        }

        return wines;


    }
}
