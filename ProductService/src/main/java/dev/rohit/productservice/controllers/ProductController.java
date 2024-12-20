package dev.rohit.productservice.controllers;

import dev.rohit.productservice.clients.internal.AuthenticationServiceClient;
import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.dtos.ProductDetailsResponseDto;
import dev.rohit.productservice.dtos.ProductPaginationRequestDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.models.ProductDetails;
import dev.rohit.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;
    private AuthenticationServiceClient authenticationServiceClient;

    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationServiceClient authenticationServiceClient) {
        this.productService = productService;
        this.authenticationServiceClient = authenticationServiceClient;
    }

    @GetMapping("/search/{keyword}")
    public Iterable<Product> searchProducts(@PathVariable String keyword) {
        return productService.searchProducts(keyword);
    }

    @PostMapping("/paginated")
    public ResponseEntity<Page<GenericProductDto>> getProducts(@RequestBody ProductPaginationRequestDto requestDto){
        Page<Product> productPage = productService.getPaginatedProducts(
                requestDto.getNumOfResults(),
                requestDto.getOffset(),
                requestDto.getSortParamsList());
        Page<GenericProductDto> productResponseDtos = productPage.map(GenericProductDto::fromProduct);
        return new ResponseEntity<>(productResponseDtos,
                HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products.stream()
                .map(GenericProductDto::fromProduct)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") Long id){
       Optional<Product> product = productService.getProductById(id);
         if(product.isEmpty()) {
              throw new NotFoundException("Product not found");
         }
            return ResponseEntity.ok(GenericProductDto.fromProduct(product.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<GenericProductDto> addProduct(GenericProductDto product) {
        Product newProduct = productService.createProduct(product);
        return ResponseEntity.ok(GenericProductDto.fromProduct(newProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericProductDto> updateProductById(@PathVariable("id") Long id, GenericProductDto product) {
        Product updatedProduct =  productService.updateProduct(id, product);
        return ResponseEntity.ok(GenericProductDto.fromProduct(updatedProduct));
    }




}
