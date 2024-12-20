package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.ProductReviewRequestDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.models.ProductReview;
import dev.rohit.productservice.repositories.ProductReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewServiceImpl implements ProductReviewService{

    private ProductReviewRepository productReviewRepository;
    private ProductService productService;

    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository, ProductService productService) {
        this.productReviewRepository = productReviewRepository;
        this.productService = productService;
    }


    @Override
    public ProductReview createReview(ProductReviewRequestDto reviewDto) {
        ProductReview review = ProductReviewRequestDto.toProductReview(reviewDto);
        Optional<Product> product = productService.getProductById(reviewDto.getProductId());
        if(product.isEmpty()){
            throw new NotFoundException("Product not found" + reviewDto.getProductId());
        }
        review.setProduct(product.get());
        return productReviewRepository.save(review);
    }

    @Override
    public ProductReview getReviewById(Long id) {
        return productReviewRepository.findById(id).orElseThrow(() -> new NotFoundException("Review not found"));
    }

    @Override
    public List<ProductReview> getAllReviews() {
        return productReviewRepository.findAll();
    }

    @Override
    public ProductReview updateReview(Long id, ProductReviewRequestDto reviewDto) {
        ProductReview review = getReviewById(id);
        review.setReview(reviewDto.getReview());
        review.setRating(reviewDto.getRating());
        return productReviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        productReviewRepository.deleteById(id);
    }

    @Override
    public List<ProductReview> getReviewsByProduct(Long productId) {
        Optional<Product> product = productService.getProductById(productId);
        if(product.isEmpty()){
            throw new NotFoundException("Product not found" + productId);
        }
        return productReviewRepository.findByProduct(product.get());
    }
}
