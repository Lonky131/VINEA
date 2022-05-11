package com.isproject.winestore.models;

public class HasCategoryValue {

    private String value;
    private long categoryId;
    private long wineId;

    public HasCategoryValue(String value, long categoryId, long wineId) {
        this.value = value;
        this.categoryId = categoryId;
        this.wineId = wineId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getWineId() {
        return wineId;
    }

    public void setWineId(long wineId) {
        this.wineId = wineId;
    }
}
