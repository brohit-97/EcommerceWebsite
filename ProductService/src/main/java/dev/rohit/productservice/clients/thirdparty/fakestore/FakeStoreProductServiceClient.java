package dev.rohit.productservice.clients.thirdparty.fakestore;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FakeStoreProductServiceClient {

    private final RestTemplateBuilder restTemplateBuilder;
    private final String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private final String productBaseUrl = "https://fakestoreapi.com/products";


    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto productDto = response.getBody();
        if(productDto == null) {
            throw new NotFoundException("Product not found");
        }
        return productDto;
    }

    public List<FakeStoreProductDto> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productBaseUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] products = response.getBody();
        System.out.println("Getting all products");
        return new ArrayList<>(List.of(products));
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        ResponseEntity<FakeStoreProductDto> response =  restTemplateBuilder.build().postForEntity(productBaseUrl, product, FakeStoreProductDto.class);
        return response.getBody();
    }


    public void updateProduct() {
        System.out.println("Updating product");
    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback,responseExtractor,id);
        return response.getBody();
    }
}
