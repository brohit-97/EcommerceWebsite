package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;


    public ProductController( @Qualifier("fakeStoreProductService")ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<GenericProductDto> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
       return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("")
    public GenericProductDto addProduct(GenericProductDto product) {
        return productService.createProduct(product);
    }

    @PostMapping("/{id}")
    public void updateProductById(@PathVariable("id") Long id) {}


}
