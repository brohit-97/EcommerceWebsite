package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.ProductReviewRequestDto;
import dev.rohit.productservice.dtos.ProductReviewResponseDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.ProductReview;
import dev.rohit.productservice.services.ProductReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductReviewControllerTest {

    @Mock
    private ProductReviewService productReviewService;

    @InjectMocks
    private ProductReviewController productReviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReviewsByProduct_returnsListOfReviews() {
        ProductReview review = new ProductReview();
        review.setId(1L);
        review.setId(1L);

        when(productReviewService.getReviewsByProduct(1L)).thenReturn(List.of(review));

        ResponseEntity<List<ProductReviewResponseDto>> response = productReviewController.getReviewsByProduct(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getReviews_returnsListOfAllReviews() {
        ProductReview review = new ProductReview();
        review.setId(1L);

        when(productReviewService.getAllReviews()).thenReturn(List.of(review));

        ResponseEntity<List<ProductReviewResponseDto>> response = productReviewController.getReviews();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getReviewById_returnsReview() {
        ProductReview review = new ProductReview();
        review.setId(1L);

        when(productReviewService.getReviewById(1L)).thenReturn(review);

        ResponseEntity<ProductReviewResponseDto> response = productReviewController.getReviewById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getReviewById_throwsNotFoundException() {
        when(productReviewService.getReviewById(1L)).thenThrow(new NotFoundException("Review not found"));

        assertThrows(NotFoundException.class, () -> productReviewController.getReviewById(1L));
    }

    @Test
    void updateReview_updatesAndReturnsReview() {
        ProductReview review = new ProductReview();
        review.setId(1L);

        ProductReviewRequestDto requestDto = new ProductReviewRequestDto();
        requestDto.setReview("Updated review");

        when(productReviewService.updateReview(1L, requestDto)).thenReturn(review);

        ResponseEntity<ProductReviewResponseDto> response = productReviewController.updateReview(1L, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createReview_createsAndReturnsReview() {
        ProductReview review = new ProductReview();
        review.setId(1L);

        ProductReviewRequestDto requestDto = new ProductReviewRequestDto();
        requestDto.setReview("New review");

        when(productReviewService.createReview(requestDto)).thenReturn(review);

        ResponseEntity<ProductReviewResponseDto> response = productReviewController.createReview(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteReview_deletesReview() {
        doNothing().when(productReviewService).deleteReview(1L);

        ResponseEntity<Void> response = productReviewController.deleteReview(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}