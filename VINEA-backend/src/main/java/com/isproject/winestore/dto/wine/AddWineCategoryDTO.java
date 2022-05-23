package com.isproject.winestore.dto.wine;

public class AddWineCategoryDTO {

    private long categoryId;
    private String value;

    public AddWineCategoryDTO(long categoryId, String value) {
        this.categoryId = categoryId;
        this.value = value;
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
