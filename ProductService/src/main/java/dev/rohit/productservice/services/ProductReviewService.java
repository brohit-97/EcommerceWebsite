package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.ProductReviewRequestDto;
import dev.rohit.productservice.models.ProductReview;

import java.util.List;

public interface ProductReviewService {
    ProductReview createReview(ProductReviewRequestDto reviewDto);
    ProductReview getReviewById(Long id);
    List<ProductReview> getAllReviews();
    ProductReview updateReview(Long id, ProductReviewRequestDto reviewDto);
    void deleteReview(Long id);
    List<ProductReview> getReviewsByProduct(Long productId);
}
