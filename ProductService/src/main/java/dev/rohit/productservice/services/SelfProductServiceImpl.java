package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDto> getProducts() {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }


    @Override
    public void updateProduct() {

    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
    return null;
    }
}
