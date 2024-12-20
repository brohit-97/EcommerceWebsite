package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.ProductDetailsRequestDto;
import dev.rohit.productservice.dtos.ProductDetailsResponseDto;
import dev.rohit.productservice.dtos.ProductReviewRequestDto;
import dev.rohit.productservice.dtos.ProductReviewResponseDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.ProductDetails;
import dev.rohit.productservice.models.ProductReview;
import dev.rohit.productservice.services.ProductDetailsService;
import dev.rohit.productservice.services.ProductReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ProductReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ProductReviewController.class);
    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductReviewResponseDto>> getReviewsByProduct(@PathVariable Long productId){
        logger.info("Getting reviews for product with id: {}", productId);
        List<ProductReview> productReview = productReviewService.getReviewsByProduct(productId);
        return ResponseEntity.ok(productReview.stream()
                .map(ProductReviewResponseDto::fromProductReview)
                .collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<ProductReviewResponseDto>> getReviews() {
        logger.info("Getting all product reviews");
        List<ProductReviewResponseDto> reviewResponseDtos = productReviewService.getAllReviews().stream()
                .map(ProductReviewResponseDto::fromProductReview)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReviewResponseDto> getReviewById(@PathVariable Long id) {
        logger.info("Getting product review by id: {}", id);
        ProductReview review = productReviewService.getReviewById(id);
        return ResponseEntity.ok(ProductReviewResponseDto.fromProductReview(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductReviewResponseDto> updateReview(@PathVariable Long id, @RequestBody ProductReviewRequestDto review) {
        logger.info("Updating product review by id: {} with data: {}", id, review);
        return ResponseEntity.ok(ProductReviewResponseDto.fromProductReview(productReviewService.updateReview(id, review)));
    }

    @PostMapping
    public ResponseEntity<ProductReviewResponseDto> createReview(@RequestBody ProductReviewRequestDto review) {
        logger.info("Creating new product review: {}", review);
        return ResponseEntity.ok(ProductReviewResponseDto.fromProductReview(productReviewService.createReview(review)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        logger.info("Deleting product review by id: {}", id);
        productReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}