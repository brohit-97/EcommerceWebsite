package dev.rohit.productservice.services;

import dev.rohit.productservice.clients.thirdparty.fakestore.FakeStoreProductDto;
import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.exceptions.ServiceException;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.models.SortParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fakeStoreProductService")
public class FakeStoreProductService  implements ProductService {

    private final RestTemplate restTemplate;
    @Value("${fakestore.api.url}")
    private final String productBaseUrl = "https://fakestoreapi.com/products";


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<Product> getProductById(Long id) throws NotFoundException {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productBaseUrl+"/products/"+id, FakeStoreProductDto.class, id);
        FakeStoreProductDto productDto = response.getBody();
        if(productDto == null) {
            throw new NotFoundException("Product not found");
        }
        return Optional.of(FakeStoreProductDto.toProduct(productDto));
    }

    @Override
    public List<Product> getProducts() {
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productBaseUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] products = response.getBody();
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto product : products) {
           productList.add(FakeStoreProductDto.toProduct(product));
        }
        System.out.println("Getting all products");
        return productList;
    }

    @Override
    public Page<Product> getPaginatedProducts(int numOfResults, int offset, List<SortParam> sortParamsList) {
        // Not implemented use self product service
        return null;
    }

    @Override
    public Product createProduct(GenericProductDto product) {
       ResponseEntity<Product> response =  restTemplate.postForEntity(productBaseUrl, product, Product.class);
       return response.getBody();
    }



    @Override
    public Product updateProduct(Long id, GenericProductDto product) {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(productBaseUrl+"/products/"+id, HttpMethod.PUT, requestCallback,responseExtractor,FakeStoreProductDto.fromGenericProductDto(product),product.getId());
        if(response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new ServiceException("Unable to update product");
        }
        return FakeStoreProductDto.toProduct(response.getBody());
    }

    @Override
    public Product deleteProduct(Long id) {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
                ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(productBaseUrl+"/products/"+id, HttpMethod.DELETE, requestCallback,responseExtractor,id);
        if(response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new ServiceException("Unable to delete product");
        }
        return FakeStoreProductDto.toProduct(response.getBody());
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        // Not implemented use self product service
        return List.of();
    }

}
