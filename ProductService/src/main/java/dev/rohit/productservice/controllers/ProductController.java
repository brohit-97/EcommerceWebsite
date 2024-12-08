package dev.rohit.productservice.controllers;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @GetMapping()
    public void getAllProducts() {}

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Product" + id ;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {}

    @PostMapping("")
    public void addProduct() {}

    @PostMapping("/{id}")
    public void updateProductById(@PathVariable("id") Long id) {}


}
