package dev.rohit.orderservice.services;

import dev.rohit.orderservice.models.Order;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    Order createOrder(Authentication authentication);

    Order getOrder(Long id);

    Order cancelOrder(Long id);

    Order completeOrder(Long id);

    List<Order> getOrderForCustomer(Authentication authentication);

}
