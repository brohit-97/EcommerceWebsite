package dev.rohit.productservice.dtos;

import java.util.List;

public class CategoryRequestDto {
    private String name;
    private List<GenericProductDto> products;

    public CategoryRequestDto() {
    }

    public CategoryRequestDto(String name, List<GenericProductDto> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GenericProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<GenericProductDto> products) {
        this.products = products;
    }
}
