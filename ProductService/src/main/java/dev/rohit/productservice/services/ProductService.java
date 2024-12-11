package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getProducts();

    GenericProductDto createProduct(GenericProductDto product);

    void updateProduct();

    GenericProductDto deleteProduct(Long id);


}
