package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.ProductDetailsRequestDto;
import dev.rohit.productservice.dtos.ProductDetailsResponseDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.ProductDetails;
import dev.rohit.productservice.services.ProductDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductDetailsControllerTest {

    @Mock
    private ProductDetailsService productDetailsService;

    @InjectMocks
    private ProductDetailsController productDetailsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductDetails_returnsListOfDetails() {
        ProductDetails details = new ProductDetails();
        details.setId(1L);

        when(productDetailsService.getAllProductDetails()).thenReturn(List.of(details));

        ResponseEntity<List<ProductDetailsResponseDto>> response = productDetailsController.getAllProductDetails();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getProductDetailsById_returnsDetails() {
        ProductDetails details = new ProductDetails();
        details.setId(1L);

        when(productDetailsService.getProductDetails(1L)).thenReturn(details);

        ResponseEntity<ProductDetailsResponseDto> response = productDetailsController.getProductDetailsById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getProductDetailsById_throwsNotFoundException() {
        when(productDetailsService.getProductDetails(1L)).thenThrow(new NotFoundException("Details not found"));

        assertThrows(NotFoundException.class, () -> productDetailsController.getProductDetailsById(1L));
    }

    @Test
    void updateProductDetails_updatesAndReturnsDetails() {
        ProductDetails details = new ProductDetails();
        details.setId(1L);

        ProductDetailsRequestDto requestDto = new ProductDetailsRequestDto();
        requestDto.setKey("Updated details");

        when(productDetailsService.updateProductDetails(1L, requestDto)).thenReturn(details);

        ResponseEntity<ProductDetailsResponseDto> response = productDetailsController.updateProductDetails(1L, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createProductDetails_createsAndReturnsDetails() {
        ProductDetails details = new ProductDetails();
        details.setId(1L);

        ProductDetailsRequestDto requestDto = new ProductDetailsRequestDto();

        when(productDetailsService.createProductDetails(requestDto)).thenReturn(details);

        ResponseEntity<ProductDetailsResponseDto> response = productDetailsController.createProductDetails(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteProductDetails_deletesDetails() {
        doNothing().when(productDetailsService).deleteProductDetails(1L);

        ResponseEntity<Void> response = productDetailsController.deleteProductDetails(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void searchProductDetailsByKeyAndValue_returnsListOfDetails() throws NotFoundException {
        ProductDetails details = new ProductDetails();
        details.setId(1L);

        when(productDetailsService.searchProductDetailsByKeyAndValue("key", "value")).thenReturn(List.of(details));

        ResponseEntity<List<ProductDetailsResponseDto>> response = productDetailsController.searchProductDetailsByKeyAndValue("key", "value");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void searchProductDetailsByKeyAndValue_throwsNotFoundException() throws NotFoundException {
        when(productDetailsService.searchProductDetailsByKeyAndValue("key", "value")).thenReturn(List.of());

        ResponseEntity<List<ProductDetailsResponseDto>> response = productDetailsController.searchProductDetailsByKeyAndValue("key", "value");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void getProductDetailsByProductId_returnsListOfDetails() {
        ProductDetails details = new ProductDetails();
        details.setId(1L);

        when(productDetailsService.getProductDetailsByProductId(1L)).thenReturn(List.of(details));

        ResponseEntity<List<ProductDetailsResponseDto>> response = productDetailsController.getProductDetailsByProductId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
}