package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.CategoryRequestDto;
import dev.rohit.productservice.models.Category;
import dev.rohit.productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategories();
    Optional<Category> getCategory(Long id);
    Category createCategory(CategoryRequestDto categoryRequestDto);
    Category updateCategory(Long id, String categoryName);
    void deleteCategory(Long id);
    List<Product> getProductsInCategory(String categoryName);
    Category getCategoryByName(String categoryName);
}
