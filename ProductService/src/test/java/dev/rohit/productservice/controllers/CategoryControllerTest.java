package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.CategoryRequestDto;
import dev.rohit.productservice.dtos.CategoryResponseDto;
import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.Category;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategories_returnsListOfCategories() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryService.getCategories()).thenReturn(List.of(category));

        ResponseEntity<List<CategoryResponseDto>> response = categoryController.getCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Electronics", response.getBody().get(0).getName());
    }

    @Test
    void getCategoryById_returnsCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryService.getCategory(1L)).thenReturn(Optional.of(category));

        ResponseEntity<CategoryResponseDto> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Electronics", response.getBody().getName());
    }

    @Test
    void getCategoryById_throwsNotFoundException() {
        when(categoryService.getCategory(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categoryController.getCategoryById(1L));
    }

    @Test
    void updateCategory_updatesAndReturnsCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName("Home Appliances");

        when(categoryService.updateCategory(1L, "Home Appliances")).thenReturn(category);

        ResponseEntity<CategoryResponseDto> response = categoryController.updateCategory(1L, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Electronics", response.getBody().getName());
    }

    @Test
    void createCategory_createsAndReturnsCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName("Electronics");

        when(categoryService.createCategory(requestDto)).thenReturn(category);

        ResponseEntity<CategoryResponseDto> response = categoryController.createCategory(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Electronics", response.getBody().getName());
    }

    @Test
    void getProductsByCategory_returnsListOfProducts() throws NotFoundException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Laptop");

        when(categoryService.getProductsInCategory("Electronics")).thenReturn(List.of(product));

        ResponseEntity<List<GenericProductDto>> response = categoryController.getProductsByCategory("Electronics");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Laptop", response.getBody().get(0).getTitle());
    }

    @Test
    void getProductsByCategory_throwsNotFoundException() throws NotFoundException {
        when(categoryService.getProductsInCategory("NonExistentCategory")).thenReturn(Collections.emptyList());

        ResponseEntity<List<GenericProductDto>> response = categoryController.getProductsByCategory("NonExistentCategory");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }
}