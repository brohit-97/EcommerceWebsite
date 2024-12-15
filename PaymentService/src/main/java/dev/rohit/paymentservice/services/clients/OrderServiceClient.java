package dev.rohit.paymentservice.services.clients;

import dev.rohit.paymentservice.dtos.OrderDto;

public interface OrderServiceClient {

    OrderDto getOrder(Long orderId, String token);
    OrderDto updateOrderWithPayment(Long orderId, String token, Long paymentId);
    OrderDto updateOrderStatus(Long orderId, String token, String status);
}
