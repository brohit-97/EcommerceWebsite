package dev.rohit.productservice.dtos;


import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.models.ProductReview;

public class ProductReviewRequestDto {


    private String review;
    private int rating;
    private Long productId;
    private Long customerId;

    public ProductReviewRequestDto() {
    }

    public ProductReviewRequestDto(String review, int rating, Long productId, Long customerId) {

        this.review = review;
        this.rating = rating;
        this.productId = productId;
        this.customerId = customerId;
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

    public static ProductReview toProductReview(ProductReviewRequestDto reviewDto){
        ProductReview review = new ProductReview();
        review.setReview(reviewDto.getReview());
        review.setRating(reviewDto.getRating());
        review.setCustomerId(reviewDto.getCustomerId());
        return review;
    }


}
