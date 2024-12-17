package dev.rohit.orderservice.controllers;

import dev.rohit.orderservice.dtos.OrderDto;

import dev.rohit.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(Authentication authentication) {
        return ResponseEntity.ok(OrderDto.from(orderService.createOrder(authentication)));
    }

    @PostMapping("/cancel")
    public void cancelOrder() {
        orderService.cancelOrder();
    }

    @PostMapping("/complete")
    public void completeOrder() {
        orderService.completeOrder();
    }


}
