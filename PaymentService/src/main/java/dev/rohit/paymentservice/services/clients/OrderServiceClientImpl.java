package dev.rohit.paymentservice.services.clients;

import dev.rohit.paymentservice.dtos.OrderDto;
import dev.rohit.paymentservice.exceptions.ServiceCallFailed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class OrderServiceClientImpl implements OrderServiceClient {

    private RestTemplate restTemplate;

    @Value("${service.order.url}")
    private String orderServiceUrl;

    public OrderServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public OrderDto getOrder(Long orderId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.valueOf("application/json"));
        ResponseEntity<OrderDto> response = restTemplate.exchange(orderServiceUrl + "/orders/" + orderId, HttpMethod.GET, new HttpEntity<>(headers), OrderDto.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new ServiceCallFailed("Failed to get order form order service");
        }
        return response.getBody();
    }

    @Override
    public OrderDto updateOrderWithPayment(Long orderId, String token, Long paymentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.valueOf("application/json"));
        ResponseEntity<OrderDto> response = restTemplate.exchange(orderServiceUrl + "/orders/" + orderId + "/payment/" + paymentId, HttpMethod.PUT, new HttpEntity<>(headers), OrderDto.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new ServiceCallFailed("Failed to update order with payment from order service");
        }
        return response.getBody();
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, String token, String status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.valueOf("application/json"));
        ResponseEntity<OrderDto> response = restTemplate.exchange(orderServiceUrl + "/orders/" + orderId + "/status", HttpMethod.PUT, new HttpEntity<>(status,headers), OrderDto.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new ServiceCallFailed("Failed to update order status from order service");
        }
        return response.getBody();
    }
}
