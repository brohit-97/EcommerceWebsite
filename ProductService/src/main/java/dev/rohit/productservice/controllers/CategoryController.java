package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.CategoryRequestDto;
import dev.rohit.productservice.dtos.CategoryResponseDto;
import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.Category;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories().stream()
                .map(CategoryResponseDto::fromCategory)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
         Optional<Category> category = categoryService.getCategory(id);
            if(category.isEmpty()) {
                throw new NotFoundException("Category not found");
            }
            return ResponseEntity.ok(CategoryResponseDto.fromCategory(category.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto category) {
        return ResponseEntity.ok(CategoryResponseDto.fromCategory(categoryService.updateCategory(id, category.getName())));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto category) {
        return ResponseEntity.ok(CategoryResponseDto.fromCategory(categoryService.createCategory(category)));
    }

    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<GenericProductDto>> getProductsByCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
            List<Product> products = categoryService.getProductsInCategory(categoryName);
            List<GenericProductDto> responseDtos = products.stream().map(GenericProductDto::fromProduct).collect(Collectors.toList());
            return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}
