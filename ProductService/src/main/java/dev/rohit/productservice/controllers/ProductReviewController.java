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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductReviewResponseDto>> getReviewsByProduct(@PathVariable Long productId){
        List<ProductReview> productReview = productReviewService.getReviewsByProduct(productId);
        return ResponseEntity.ok(productReview.stream()
                .map(ProductReviewResponseDto::fromProductReview)
                .collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<ProductReviewResponseDto>> getReviews() {
        List<ProductReviewResponseDto> reviewResponseDtos = productReviewService.getAllReviews().stream()
                .map(ProductReviewResponseDto::fromProductReview)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReviewResponseDto> getReviewById(@PathVariable Long id) {
        ProductReview review = productReviewService.getReviewById(id);
        return ResponseEntity.ok(ProductReviewResponseDto.fromProductReview(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductReviewResponseDto> updateReview(@PathVariable Long id, @RequestBody ProductReviewRequestDto review) {
        return ResponseEntity.ok(ProductReviewResponseDto.fromProductReview(productReviewService.updateReview(id, review)));
    }

    @PostMapping
    public ResponseEntity<ProductReviewResponseDto> createReview(@RequestBody ProductReviewRequestDto review) {
        return ResponseEntity.ok(ProductReviewResponseDto.fromProductReview(productReviewService.createReview(review)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        productReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
