package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.CategoryRequestDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.Category;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        Category category = categoryOptional.get();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        if(categoryOptional.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        Category category = categoryOptional.get();
        return category.getProducts();
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }
}
