package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.ProductDetailsRequestDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.ProductDetails;
import dev.rohit.productservice.repositories.ProductDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService{

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductService productService;

    public ProductDetailsServiceImpl(ProductDetailsRepository productDetailsRepository, ProductService productService) {
        this.productDetailsRepository = productDetailsRepository;
        this.productService = productService;
    }

    @Override
    public List<ProductDetails> getAllProductDetails() {
       return productDetailsRepository.findAll();
    }

    @Override
    public List<ProductDetails> searchProductDetailsByKeyAndValue(String key, String value) {
        return productDetailsRepository.findByKeyAndValue(key, value);
    }

    @Override
    public ProductDetails createProductDetails(ProductDetailsRequestDto specificationDto) {
        ProductDetails productDetails = ProductDetailsRequestDto.toProductDetails(specificationDto);
        productDetails.setProduct(productService.getProductById(specificationDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return productDetailsRepository.save(productDetails);
    }

    @Override
    public ProductDetails updateProductDetails(Long id, ProductDetailsRequestDto updatedSpecDto) {
        ProductDetails productDetails = productDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Details not found"));
        productDetails.setKey(updatedSpecDto.getKey());
        productDetails.setValue(updatedSpecDto.getValue());
        return productDetailsRepository.save(productDetails);
    }

    @Override
    public void deleteProductDetails(Long id) {
        productDetailsRepository.deleteById(id);
    }

    @Override
    public ProductDetails getProductDetails(Long id) {
        return productDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Details not found"));
    }

    @Override
    public List<ProductDetails> getProductDetailsByProductId(Long productId) {
        return productDetailsRepository.findByProductId(productId);
    }
}
