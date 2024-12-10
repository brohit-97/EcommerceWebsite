package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;


    public ProductController( @Qualifier("fakeStoreProductService")ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public void getAllProducts() {}

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {
       return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {}

    @PostMapping("")
    public void addProduct() {}

    @PostMapping("/{id}")
    public void updateProductById(@PathVariable("id") Long id) {}


}
