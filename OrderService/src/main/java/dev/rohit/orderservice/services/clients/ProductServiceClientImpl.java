package dev.rohit.orderservice.services.clients;

import dev.rohit.orderservice.dtos.CartDto;
import dev.rohit.orderservice.dtos.ProductDto;
import dev.rohit.orderservice.exceptions.ServiceCallFailed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProductServiceClientImpl implements ProductServiceClient {

    @Value("${product.service.url}")
    private String productServiceUrl;

    private RestTemplate restTemplate;

    public ProductServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductDto getProductById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/json"));
        ResponseEntity<ProductDto> response = restTemplate.exchange(productServiceUrl + "/products",
                HttpMethod.GET, new HttpEntity<>(headers), ProductDto.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new ServiceCallFailed("Failed to get product details form product service");
        }
        return response.getBody();
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/json"));
        ResponseEntity<ProductDto> response = restTemplate.exchange(productServiceUrl + "/products" + productDto.getId(),
                HttpMethod.PUT, new HttpEntity<>(productDto,headers), ProductDto.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new ServiceCallFailed("Failed to update product details form product service");
        }
        return response.getBody();
    }
}
