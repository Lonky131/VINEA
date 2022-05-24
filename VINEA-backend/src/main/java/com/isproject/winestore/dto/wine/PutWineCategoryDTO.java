package com.isproject.winestore.dto.wine;

public class PutWineCategoryDTO {

    private long id;
    private long categoryId;
    private String value;

    public PutWineCategoryDTO(long id, long categoryId, String value) {
        this.id = id;
        this.categoryId = categoryId;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
