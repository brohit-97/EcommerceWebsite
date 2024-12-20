package dev.rohit.productservice.dtos;

import dev.rohit.productservice.models.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResponseDto {
    private Long id;
    private String name;
    private List<GenericProductDto> products;

    public CategoryResponseDto() {
    }

    public CategoryResponseDto(Long id, String name, List<GenericProductDto> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static CategoryResponseDto fromCategory(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName(), category.getProducts().stream().map(GenericProductDto::fromProduct).collect(Collectors.toList()));
    }

    public void setProducts(List<GenericProductDto> products) {
        this.products = products;
    }
}
