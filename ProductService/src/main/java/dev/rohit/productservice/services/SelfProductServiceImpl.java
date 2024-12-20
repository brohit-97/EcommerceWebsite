package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDto> getProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        products.forEach(product -> genericProductDtos.add(GenericProductDto.fromProduct(product)));
        return genericProductDtos;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }


    @Override
    public GenericProductDto updateProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
    return null;
    }
}
