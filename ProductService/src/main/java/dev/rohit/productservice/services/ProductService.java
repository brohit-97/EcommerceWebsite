package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.models.Product;

public interface ProductService {

    GenericProductDto getProductById(Long id);

    void getProducts();

    void createProduct();

    void updateProduct();

    void deleteProduct();


}
