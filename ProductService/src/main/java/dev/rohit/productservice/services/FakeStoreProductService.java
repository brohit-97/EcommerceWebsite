package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.FakeStoreProductDto;
import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService  implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;
    private final String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private final String productBaseUrl = "https://fakestoreapi.com/products";


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto productDto = response.getBody();
        if(productDto == null) {
            throw new NotFoundException("Product not found");
        }
        return productDto.toGenericProductDto();
    }

    @Override
    public List<GenericProductDto> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productBaseUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] products = response.getBody();
        List<GenericProductDto> genericProductDtoResponse = new ArrayList<>();
        for (FakeStoreProductDto product : products) {
           genericProductDtoResponse.add(product.toGenericProductDto());
        }
        System.out.println("Getting all products");
        return genericProductDtoResponse;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
       ResponseEntity<GenericProductDto> response =  restTemplateBuilder.build().postForEntity(productBaseUrl, product, GenericProductDto.class);
       return response.getBody();
    }

    @Override
    public void updateProduct() {
        System.out.println("Updating product");
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
                ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback,responseExtractor,id);
                return response.getBody().toGenericProductDto();
    }
}
