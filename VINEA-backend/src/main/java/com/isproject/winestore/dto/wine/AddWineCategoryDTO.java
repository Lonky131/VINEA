package com.isproject.winestore.dto.wine;

import com.isproject.winestore.models.WineCategory;

public class AddWineCategoryDTO {

    private long categoryId;
    private String value;

    public AddWineCategoryDTO(long categoryId, String value) {
        this.categoryId = categoryId;
        this.value = value;
    }

    public AddWineCategoryDTO(WineCategory wineCategory) {
        this.categoryId = wineCategory.getCategory().getId();
        this.value = wineCategory.getValue();
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
