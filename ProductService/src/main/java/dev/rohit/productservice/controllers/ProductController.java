package dev.rohit.productservice.controllers;

import dev.rohit.productservice.clients.internal.AuthenticationServiceClient;
import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.dtos.ProductPaginationRequestDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final AuthenticationServiceClient authenticationServiceClient;

    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationServiceClient authenticationServiceClient) {
        this.productService = productService;
        this.authenticationServiceClient = authenticationServiceClient;
    }

    @GetMapping("/search/{keyword}")
    public Iterable<Product> searchProducts(@PathVariable String keyword) {
        logger.info("Searching products with keyword: {}", keyword);
        return productService.searchProducts(keyword);
    }

    @PostMapping("/paginated")
    public ResponseEntity<Page<GenericProductDto>> getProducts(@RequestBody ProductPaginationRequestDto requestDto) {
        logger.info("Getting paginated products with request: {}", requestDto);
        Page<Product> productPage = productService.getPaginatedProducts(
                requestDto.getNumOfResults(),
                requestDto.getOffset(),
                requestDto.getSortParamsList());
        Page<GenericProductDto> productResponseDtos = productPage.map(GenericProductDto::fromProduct);
        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        logger.info("Getting all products");
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products.stream()
                .map(GenericProductDto::fromProduct)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") Long id) {
        logger.info("Getting product by id: {}", id);
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            logger.warn("Product not found with id: {}", id);
            throw new NotFoundException("Product not found");
        }
        return ResponseEntity.ok(GenericProductDto.fromProduct(product.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id) {
        logger.info("Deleting product by id: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<GenericProductDto> addProduct(@RequestBody GenericProductDto product) {
        logger.info("Adding new product: {}", product);
        Product newProduct = productService.createProduct(product);
        return ResponseEntity.ok(GenericProductDto.fromProduct(newProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericProductDto> updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto product) {
        logger.info("Updating product by id: {} with data: {}", id, product);
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(GenericProductDto.fromProduct(updatedProduct));
    }
}