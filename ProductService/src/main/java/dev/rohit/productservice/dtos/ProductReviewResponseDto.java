package dev.rohit.productservice.dtos;

import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.models.ProductReview;


public class ProductReviewResponseDto {
    private Long id;
    private String review;
    private int rating;
    private Long productId;
    private Long customerId;

    public ProductReviewResponseDto() {
    }

    public ProductReviewResponseDto(Long id, String review, int rating, Long productId, Long customerId) {
        this.id = id;
        this.review = review;
        this.rating = rating;
        this.productId = productId;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public static ProductReviewResponseDto fromProductReview(ProductReview product) {
        return new ProductReviewResponseDto(product.getId(), product.getReview(), product.getRating(),
                product.getProduct().getId(), product.getCustomerId());
    }

}
