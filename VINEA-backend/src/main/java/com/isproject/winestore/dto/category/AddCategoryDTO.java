package com.isproject.winestore.dto.category;

public class AddCategoryDTO {

    private String name;

    public AddCategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
