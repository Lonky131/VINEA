package com.isproject.winestore.repos;

import com.isproject.winestore.models.Category;
import com.isproject.winestore.models.Wine;
import com.isproject.winestore.models.Winery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WineRepo {

    private final JdbcTemplate jdbcTemplate;

    public WineRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wine> getWines() {

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SELECT * FROM wines"
        );

        List<Wine> wines = new ArrayList<>();

        for (Map row : rows) {
            long id = Long.parseLong(row.get("id").toString());
            String name = row.get("name").toString();
            int productionYear = Integer.parseInt(row.get("production_year").toString());
            double alcoholPercentage = Double.parseDouble(row.get("alcohol_percentage").toString());
            int volume = Integer.parseInt(row.get("volume").toString());
            double price = Double.parseDouble(row.get("price").toString());
            String pictureUrl = row.get("picture_url").toString();
            long wineryId = Long.parseLong(row.get("winery_id").toString());
            Winery winery = jdbcTemplate.queryForObject("SELECT * FROM wineries WHERE id = ?",
                    new Object[] {wineryId},
                    (rs, rowNum) ->
                        new Winery(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("founding_year"),
                                rs.getLong("region_id")
                        )
            );
            Wine wine = new Wine(id, name, productionYear,alcoholPercentage, volume, price, pictureUrl, winery);
            wines.add(wine);
        }

        return wines;
    }

    public List<Wine> getWinesByName(String name) {

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SELECT * FROM wines WHERE LOWER(name) LIKE CONCAT('%', ?, '%')",
                new Object[] {name.toLowerCase()}
        );

        List<Wine> wines = new ArrayList<>();

        for (Map row : rows) {
            long id = Long.parseLong(row.get("id").toString());
            String wineName = row.get("name").toString();
            int productionYear = Integer.parseInt(row.get("production_year").toString());
            double alcoholPercentage = Double.parseDouble(row.get("alcohol_percentage").toString());
            int volume = Integer.parseInt(row.get("volume").toString());
            double price = Double.parseDouble(row.get("price").toString());
            String pictureUrl = row.get("picture_url").toString();
            long wineryId = Long.parseLong(row.get("winery_id").toString());
            Winery winery = jdbcTemplate.queryForObject("SELECT * FROM wineries WHERE id = ?",
                    new Object[] {wineryId},
                    (rs, rowNum) ->
                            new Winery(
                                    rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getInt("founding_year"),
                                    rs.getLong("region_id")
                            )
            );
            Wine wine = new Wine(id, wineName, productionYear,alcoholPercentage, volume, price, pictureUrl, winery);
            wines.add(wine);
        }

        return wines;
    }

    public List<Wine> getWinesByColor(String color) {

        Category category = jdbcTemplate.queryForObject("SELECT * FROM categories WHERE name = 'boja'",
                (rs, rowNum) ->
                        new Category(
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

        List<Wine> wines = new ArrayList<>();

        for (Map row : rows) {
            long id = Long.parseLong(row.get("id").toString());
            String wineName = row.get("name").toString();
            int productionYear = Integer.parseInt(row.get("production_year").toString());
            double alcoholPercentage = Double.parseDouble(row.get("alcohol_percentage").toString());
            int volume = Integer.parseInt(row.get("volume").toString());
            double price = Double.parseDouble(row.get("price").toString());
            String pictureUrl = row.get("picture_url").toString();
            long wineryId = Long.parseLong(row.get("winery_id").toString());
            Winery winery = jdbcTemplate.queryForObject("SELECT * FROM wineries WHERE id = ?",
                    new Object[] {wineryId},
                    (rs, rowNum) ->
                            new Winery(
                                    rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getInt("founding_year"),
                                    rs.getLong("region_id")
                            )
            );
            Wine wine = new Wine(id, wineName, productionYear,alcoholPercentage, volume, price, pictureUrl, winery);
            wines.add(wine);
        }

        return wines;


    }
}
