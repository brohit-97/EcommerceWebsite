package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.models.SortParam;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> getProductById(Long id);

    List<Product> getProducts();

    Product createProduct(GenericProductDto product);

    Product updateProduct(Long id, GenericProductDto product);

    Product deleteProduct(Long id);
    List<Product> searchProducts(String keyword);
    Page<Product> getPaginatedProducts(int numOfResults, int offset, List<SortParam> sortParamsList);
}
