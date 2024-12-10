package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public void getProducts() {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProduct() {

    }

    @Override
    public void deleteProduct() {

    }
}
