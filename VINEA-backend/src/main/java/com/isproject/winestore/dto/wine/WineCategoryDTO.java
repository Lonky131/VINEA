package com.isproject.winestore.dto.wine;

import com.isproject.winestore.models.WineCategory;

public class WineCategoryDTO {

    private long id;
    private long categoryId;
    private String categoryName;
    private String value;

    public WineCategoryDTO(long id, long categoryId, String categoryName, String value) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.value = value;
    }

    public WineCategoryDTO(WineCategory wineCategory) {
        this.id = wineCategory.getId();
        this.categoryId = wineCategory.getCategory().getId();
        this.categoryName = wineCategory.getCategory().getName();
        this.value = wineCategory.getValue();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
