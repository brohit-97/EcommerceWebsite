package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.CategoryResponseDto;
import dev.rohit.productservice.dtos.ProductDetailsRequestDto;
import dev.rohit.productservice.dtos.ProductDetailsResponseDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.ProductDetails;
import dev.rohit.productservice.services.ProductDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/details")
public class ProductDetailsController {
    private final ProductDetailsService productDetailsService;

    public ProductDetailsController(ProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailsResponseDto>> getAllProductDetails() {
        List<ProductDetailsResponseDto> detailsResponseDtos = productDetailsService.getAllProductDetails().stream()
                .map(ProductDetailsResponseDto::toProductDetailsResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(detailsResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsResponseDto> getProductDetailsById(@PathVariable("id") Long id) {
        ProductDetails details = productDetailsService.getProductDetails(id);
        return ResponseEntity.ok(ProductDetailsResponseDto.toProductDetailsResponseDto(details));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailsResponseDto> updateProductDetails(@PathVariable("id") Long id, @RequestBody ProductDetailsRequestDto details) {
        return ResponseEntity.ok(ProductDetailsResponseDto.toProductDetailsResponseDto(productDetailsService.updateProductDetails(id, details)));
    }

    @PostMapping
    public ResponseEntity<ProductDetailsResponseDto> createProductDetails(@RequestBody ProductDetailsRequestDto details) {
        return ResponseEntity.ok(ProductDetailsResponseDto.toProductDetailsResponseDto(productDetailsService.createProductDetails(details)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductDetails(@PathVariable("id") Long id) {
        productDetailsService.deleteProductDetails(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{key}/{value}")
    public ResponseEntity<List<ProductDetailsResponseDto>> searchProductDetailsByKeyAndValue(
            @PathVariable String key,
            @PathVariable String value) throws NotFoundException {
        List<ProductDetails> productDetails = productDetailsService.searchProductDetailsByKeyAndValue(key, value);
        List<ProductDetailsResponseDto> productDetailsResponseDtos = productDetails.stream()
                .map(ProductDetailsResponseDto::toProductDetailsResponseDto)
                .toList();
        return new ResponseEntity<>(productDetailsResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductDetailsResponseDto>> getProductDetailsByProductId(@PathVariable Long productId) {
        List<ProductDetails> specificationsDto = productDetailsService.getProductDetailsByProductId(productId);
        List<ProductDetailsResponseDto> productDetailsResponseDtos = specificationsDto.stream()
                .map(ProductDetailsResponseDto::toProductDetailsResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDetailsResponseDtos);
    }

}
