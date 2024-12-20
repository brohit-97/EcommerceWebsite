package dev.rohit.productservice.services;


import dev.rohit.productservice.dtos.ProductDetailsRequestDto;
import dev.rohit.productservice.models.ProductDetails;

import java.util.List;

public interface ProductDetailsService {
    List<ProductDetails> getAllProductDetails();
    List<ProductDetails> searchProductDetailsByKeyAndValue(String key, String value); ;
    ProductDetails createProductDetails(ProductDetailsRequestDto specificationDto);
    ProductDetails updateProductDetails(Long id, ProductDetailsRequestDto updatedSpecDto);
    void deleteProductDetails(Long id);
    ProductDetails getProductDetails(Long id);
    List<ProductDetails> getProductDetailsByProductId(Long productId);
}
