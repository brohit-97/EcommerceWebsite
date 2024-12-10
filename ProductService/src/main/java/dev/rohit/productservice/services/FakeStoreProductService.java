package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.FakeStoreProductDto;
import dev.rohit.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService  implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;
    private final String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto productDto = response.getBody();
        GenericProductDto product = new GenericProductDto();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setImage(productDto.getImage());
        product.setPrice(productDto.getPrice());
        return product;
    }

    @Override
    public void getProducts() {
        System.out.println("Getting all products");
    }

    @Override
    public void createProduct() {
        System.out.println("Creating product");
    }

    @Override
    public void updateProduct() {
        System.out.println("Updating product");
    }

    @Override
    public void deleteProduct() {
        System.out.println("Deleting product");
    }
}
