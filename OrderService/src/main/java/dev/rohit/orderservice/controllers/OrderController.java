package dev.rohit.orderservice.controllers;

import dev.rohit.orderservice.dtos.OrderDto;

import dev.rohit.orderservice.models.Order;
import dev.rohit.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(OrderDto.from(orderService.cancelOrder(orderId)));
    }

    @PostMapping("/complete/{orderId}")
    public  ResponseEntity<OrderDto> completeOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(OrderDto.from(orderService.completeOrder(orderId)));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(OrderDto.from(orderService.getOrder(orderId)));
    }

    @GetMapping("/customer")
    public ResponseEntity<List<OrderDto>> getOrderForCustomer(Authentication authentication) {
        List<Order> order =  orderService.getOrderForCustomer(authentication);
        if(order == null || order.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(order.stream().map(OrderDto::from).collect(Collectors.toList()));
    }

}
