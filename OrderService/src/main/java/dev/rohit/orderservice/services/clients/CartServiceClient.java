package dev.rohit.orderservice.services.clients;

import dev.rohit.orderservice.dtos.CartDto;

public interface CartServiceClient {

    CartDto getCartForCustomer(String token);

    void deleteCartForCustomer(String token);
}
