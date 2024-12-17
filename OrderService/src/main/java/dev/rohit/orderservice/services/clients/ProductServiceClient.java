package dev.rohit.orderservice.services.clients;

import dev.rohit.orderservice.dtos.ProductDto;

public interface ProductServiceClient {

    ProductDto getProductById(Long id);

    ProductDto updateProduct(ProductDto productDto);
}
