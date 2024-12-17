package dev.rohit.orderservice.services.clients;

import dev.rohit.orderservice.dtos.CartDto;
import dev.rohit.orderservice.exceptions.ServiceCallFailed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceClientImpl implements CartServiceClient {

    private RestTemplate restTemplate;

    @Value("${cart.service.url}")
    private String cartServiceUrl;

    public CartServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CartDto getCartForCustomer(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.valueOf("application/json"));
        ResponseEntity<CartDto> response = restTemplate.exchange(cartServiceUrl + "/cart/customer",
                                                HttpMethod.GET, new HttpEntity<>(headers), CartDto.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new ServiceCallFailed("Failed to get cart details form cart service");
        }
        return response.getBody();
    }

    @Override
    public void deleteCartForCustomer(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        ResponseEntity<Void> response = restTemplate.exchange(cartServiceUrl + "/cart" ,
                HttpMethod.DELETE, new HttpEntity<>(headers), Void.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new ServiceCallFailed("Failed to get cart details form cart service");
        }
    }
}
