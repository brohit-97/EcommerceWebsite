package dev.rohit.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductReview extends BaseModel{

    private String review;
    private int rating;
    @ManyToOne
    private Product product;
    private Long customerId;

    public ProductReview() {
    }

    public ProductReview(String review, int rating, Product product, Long customerId) {
        this.review = review;
        this.rating = rating;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }



}
